// src/main/java/com/tuempresa/proyecto/service/ReservaService.java
package com.JMichelD.Proyecto_Reservas_Back.service.implement;

import com.JMichelD.Proyecto_Reservas_Back.exception.ResourceNotFoundException;
import com.JMichelD.Proyecto_Reservas_Back.model.Evento;
import com.JMichelD.Proyecto_Reservas_Back.model.Reserva;
import com.JMichelD.Proyecto_Reservas_Back.repository.EventoRepository;
import com.JMichelD.Proyecto_Reservas_Back.repository.ReservaRepository;
import com.JMichelD.Proyecto_Reservas_Back.service.InterReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservaServiceImple implements InterReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private EventoRepository eventoRepository;

    // Obtener todas las reservas
    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    // Obtener reservas por usuario
    public List<Reserva> getReservasByUsuario(String usuario) {
        return reservaRepository.findByUsuarioReserva(usuario);
    }

    // Crear una reserva con validación
    @Transactional
    public Reserva createReserva(Reserva reserva) {
        // Buscar el evento al que se hace la reserva
        Evento evento = eventoRepository.findById(reserva.getEvento().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado con id: " + reserva.getEvento().getId()));

        // Validar si el evento aún tiene capacidad disponible
        int capacidad = evento.getCapacidad();
        int reservasActuales = evento.getReservasActuales();
        if (reservasActuales + reserva.getCantidad() > capacidad) {
            throw new IllegalArgumentException("El evento está completo o no tiene suficiente capacidad para la reserva solicitada.");
        }

        // Actualizar el número de reservas en el evento
        evento.setReservasActuales(reservasActuales + reserva.getCantidad());
        eventoRepository.save(evento);

        // Guardar la reserva
        return reservaRepository.save(reserva);
    }

    // Cancelar una reserva (opcional: restar la cantidad reservada del evento)
    @Transactional
    public void deleteReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con id: " + id));

        // Actualizar el evento restando la cantidad reservada
        Evento evento = reserva.getEvento();
        evento.setReservasActuales(evento.getReservasActuales() - reserva.getCantidad());
        eventoRepository.save(evento);

        // Eliminar la reserva
        reservaRepository.deleteById(id);
    }

    // Actualizar una reserva: se puede implementar según la lógica del negocio
    // (Esta operación puede ser compleja si se debe ajustar la cantidad en el evento)
}
