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

    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    public List<Reserva> getReservasByUsuario(String usuario) {
        return reservaRepository.findByUsuarioUsername(usuario);
    }

    @Transactional
    public Reserva createReserva(Reserva reserva) {

        Evento evento = eventoRepository.findById(reserva.getEvento().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado con id: " + reserva.getEvento().getId()));

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

    @Transactional
    public void deleteReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con id: " + id));

        Evento evento = reserva.getEvento();
        evento.setReservasActuales(evento.getReservasActuales() - reserva.getCantidad());
        eventoRepository.save(evento);

        reservaRepository.deleteById(id);
    }

}
