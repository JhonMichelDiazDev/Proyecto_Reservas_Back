// src/main/java/com/tuempresa/proyecto/model/Evento.java
package com.JMichelD.Proyecto_Reservas_Back.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;


@Entity
@Table(name = "TAB_USUARIO")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioReserva{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @Column(nullable = false)
    private String nombre;

    @Column(length = 2000)
    private String descripcion;
}
