package com.JMichelD.Proyecto_Reservas_Back.model.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.JMichelD.Proyecto_Reservas_Back.model.Evento;
import com.JMichelD.Proyecto_Reservas_Back.model.UsuarioReserva;
import jakarta.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReservaDto implements java.io.Serializable{

    private Long idReserva;
    
    @NotEmpty(message = "no puede estar vacío")
    private UsuarioReserva usuario;

    @NotEmpty(message = "no puede estar vacío")
    private Evento evento;

    @NotEmpty(message = "no puede estar vacío")
    private int cantidad;

}