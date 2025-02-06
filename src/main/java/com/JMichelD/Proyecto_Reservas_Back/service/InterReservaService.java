// src/main/java/com/tuempresa/proyecto/service/ReservaService.java
package com.JMichelD.Proyecto_Reservas_Back.service;

import com.JMichelD.Proyecto_Reservas_Back.model.Reserva;
import java.util.List;

 public interface InterReservaService {


    List<Reserva> getAllReservas();

    List<Reserva> getReservasByUsuario(String usuario);

    Reserva createReserva(Reserva reserva);

    void deleteReserva(Long id);
}
