package com.JMichelD.Proyecto_Reservas_Back.repository;

import com.JMichelD.Proyecto_Reservas_Back.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>  {

    @Override
    boolean existsById(Long aLong);
}