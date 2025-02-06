// src/main/java/com/tuempresa/proyecto/service/EventoService.java
package com.JMichelD.Proyecto_Reservas_Back.service.implement;

import com.JMichelD.Proyecto_Reservas_Back.model.Evento;
import com.JMichelD.Proyecto_Reservas_Back.repository.EventoRepository;
import com.JMichelD.Proyecto_Reservas_Back.service.InterEventoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceImple implements InterEventoService {

    @Autowired
    private EventoRepository eventoRepository;

    // Obtener todos los eventos
    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    // Obtener un evento por ID
    public Optional<Evento> getEventoById(Long id) {
        return eventoRepository.findById(id);
    }

    // Crear un nuevo evento
    public Evento createEvento(Evento evento) {
        // Inicialmente, reservasActuales se puede establecer en 0
        evento.setReservasActuales(0);
        return eventoRepository.save(evento);
    }

    // Actualizar un evento existente
    public Optional<Evento> updateEvento(Long id, Evento eventoDetails) {
        return eventoRepository.findById(id).map(evento -> {
            evento.setNombre(eventoDetails.getNombre());
            evento.setDescripcion(eventoDetails.getDescripcion());
            evento.setCapacidad(eventoDetails.getCapacidad());
            // Si se actualiza la capacidad, se podría agregar lógica adicional
            return eventoRepository.save(evento);
        });
    }

    // Eliminar un evento
    public void deleteEvento(Long id) {
        eventoRepository.deleteById(id);
    }
}
