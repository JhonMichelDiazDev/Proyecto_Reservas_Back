package com.JMichelD.Proyecto_Reservas_Back.model.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UsuarioReservaDto implements java.io.Serializable{

    private Long idUsuario;

    @NotEmpty(message = "no puede estar vacío")
    private String nombre;

    @NotEmpty(message = "no puede estar vacío")
    private String descripcion;

}