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

    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> getEventoById(Long id) {
        return eventoRepository.findById(id);
    }

    public Evento createEvento(Evento evento) {
        evento.setReservasActuales(0);
        return eventoRepository.save(evento);
    }

    public Optional<Evento> updateEvento(Long id, Evento eventoDetails) {
        return eventoRepository.findById(id).map(evento -> {
            evento.setNombre(eventoDetails.getNombre());
            evento.setDescripcion(eventoDetails.getDescripcion());
            evento.setCapacidad(eventoDetails.getCapacidad());

            return eventoRepository.save(evento);
        });
    }


    public void deleteEvento(Long id) {
        eventoRepository.deleteById(id);
    }
}
