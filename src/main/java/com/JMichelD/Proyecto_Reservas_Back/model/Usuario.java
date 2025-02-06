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
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long idusuario;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String username;

    @Column(length = 2000)
    private String descripcion;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String rol = "ROLE_USER";
}
