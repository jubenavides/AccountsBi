package com.jolube.exam.controller;

import com.jolube.exam.dto.EstadoCuentaDto;
import com.jolube.exam.service.MovimientoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    MovimientoService movimientoService;

    /*
     * Realizo el mismo metodo porque en el ejercicio no hay diferencias
     * entre cliente y empleado,
     * pero es mejor tener 2 endpoint separados para este tema
     */
    @GetMapping
    public EstadoCuentaDto findMovimientos(
            @RequestParam(required = false, defaultValue = "") String cliente,
            @RequestParam(required = false, defaultValue = "") String empleado
                                          ) {
        return movimientoService.findMovimientos(cliente, empleado);
    }

    @GetMapping("/cliente/{clienteId}")
    public EstadoCuentaDto findMovimientosCliente(@PathVariable String clienteId) {
        return movimientoService.findMovimientos(clienteId, "");
    }

    @GetMapping("/empleado/{empleadoId}")
    public EstadoCuentaDto findMovimientosEmpleado(@PathVariable String empleadoId) {
        return movimientoService.findMovimientos("", empleadoId);
    }

}
