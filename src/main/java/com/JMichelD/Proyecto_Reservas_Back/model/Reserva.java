// src/main/java/com/tuempresa/proyecto/model/Reserva.java
package com.JMichelD.Proyecto_Reservas_Back.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;


@Entity
@Table(name = "TAB_RESERVA")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RESERVA")
    private Long idReserva;

    @ManyToOne(optional = false)  
    @JoinColumn(name = "ID_USUARIO", nullable = false)  
    private UsuarioReserva usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EVENTO", nullable = false)
    private Evento evento;

    @Column(nullable = false)
    private int cantidad;

}
