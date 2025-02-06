
package com.JMichelD.Proyecto_Reservas_Back.controller;

import com.JMichelD.Proyecto_Reservas_Back.model.Reserva;
import com.JMichelD.Proyecto_Reservas_Back.service.implement.ReservaServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gestion/reservas")
public class ReservaController {

    @Autowired
    private ReservaServiceImple reservaService;

    @GetMapping
    public List<Reserva> getAllReservas() {
        return reservaService.getAllReservas();
    }

    @GetMapping("/usuario/{usuario}")
    public List<Reserva> getReservasByUsuario(@PathVariable String usuario) {
        return reservaService.getReservasByUsuario(usuario);
    }

    @PostMapping
    public Reserva createReserva(@RequestBody Reserva reserva) {
        return reservaService.createReserva(reserva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }
}
