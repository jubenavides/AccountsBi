package com.jolube.exam.service;

import com.jolube.exam.dto.EstadoCuentaDto;
import com.jolube.exam.exception.CustomException;
import com.jolube.exam.model.Cliente;
import com.jolube.exam.model.Cuenta;
import com.jolube.exam.model.Movimiento;
import com.jolube.exam.model.TipoMovimiento;
import com.jolube.exam.repository.ClienteRepository;
import com.jolube.exam.repository.CuentaRepository;
import com.jolube.exam.repository.MovimientoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class MovimientoService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    CuentaRepository cuentaRepository;
    
    @Autowired
    MovimientoRepository movimientoRepository;

    /*
        * Realizo la separacion entre cliente y empleado
        * sin embargo llamo al mismo metodo porque en el ejercicio no hay diferencias
        * entre cliente y empleado, pero este metodo permitiria separar la logica
        * en un futuro
     */
    public EstadoCuentaDto findMovimientos(String clienteId, String empleadoId) {
        if(clienteId.isEmpty() && empleadoId.isEmpty()){
            throw new CustomException("Debe ingresar un cliente o un empleado");
        }else if (!clienteId.isEmpty()){
            return findMovimientos(clienteId);
        } else {
            return findMovimientos(empleadoId);
        }
    }
    
    private EstadoCuentaDto findMovimientos(String clienteId) {
        if (clienteId.length() != 9) {
            throw new CustomException("El id del cliente debe tener 9 caracteres");
        }
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El cliente no existe"));
        Cuenta cuenta = cuentaRepository.findByClienteIdentificacion(cliente.getIdentificacion())
                                        .orElseThrow(() -> new ResponseStatusException(
                                                HttpStatus.NOT_FOUND,
                                                "Cliente no tiene cuentas activas"));
        List<Movimiento> movimientos = movimientoRepository.findByCuentaNumeroCuenta(cuenta.getNumeroCuenta());
        return fillEstadoCuenta(cliente, cuenta, movimientos);
    }

    private EstadoCuentaDto fillEstadoCuenta(Cliente cliente, Cuenta cuenta, List<Movimiento> movimientos){
        EstadoCuentaDto estadoCuentaDto = new EstadoCuentaDto();
        estadoCuentaDto.setNombre(cliente.getNombre());
        estadoCuentaDto.setApellido(cliente.getApellido());
        estadoCuentaDto.setEdad(cliente.getEdad());
        estadoCuentaDto.setNumeroCuenta(cuenta.getNumeroCuenta());
        estadoCuentaDto.setFechaCreacionCuenta(cuenta.getFechaCreacion());
        estadoCuentaDto.setSaldoCuenta(getSaldo(movimientos));
        estadoCuentaDto.setMovimientos(movimientos);
        return estadoCuentaDto;
    }

    public Double getSaldo(List<Movimiento> movimientos) {
        Double saldo = 0.0;
        for (Movimiento movimiento : movimientos) {
            if (movimiento.getTipo().equals(TipoMovimiento.CREDITO)) {
                saldo += movimiento.getMonto();
            } else {
                saldo -= movimiento.getMonto();
            }
        }
        return saldo;
    }
}
