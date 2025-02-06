package com.JMichelD.Proyecto_Reservas_Back.repository;

import com.JMichelD.Proyecto_Reservas_Back.model.UsuarioReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioReservaRepository extends JpaRepository<UsuarioReserva, Long>  {

    @Override
    boolean existsById(Long aLong);
}