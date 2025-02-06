package com.JMichelD.Proyecto_Reservas_Back.repository;

import com.JMichelD.Proyecto_Reservas_Back.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {

    @Override
    boolean existsById(Long aLong);

    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);
}