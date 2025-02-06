// src/main/java/com/tuempresa/proyecto/service/EventoService.java
package com.JMichelD.Proyecto_Reservas_Back.service;

import com.JMichelD.Proyecto_Reservas_Back.model.Evento;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface InterEventoService {

    Optional<Evento> getEventoById(Long id);

    Evento createEvento(Evento evento);

    Optional<Evento> updateEvento(Long id, Evento eventoDetails);
    
    void deleteEvento(Long id);
}
