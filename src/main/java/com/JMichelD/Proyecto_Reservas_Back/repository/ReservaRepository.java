package com.JMichelD.Proyecto_Reservas_Back.repository;

import com.JMichelD.Proyecto_Reservas_Back.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>  {

    @Override
    boolean existsById(Long aLong);

    
    List<Reserva> findByIdEvento(Long eventoId);

    List<Reserva> findByUsuarioUsername(String usuario);
}