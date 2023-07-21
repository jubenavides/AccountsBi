package com.jolube.exam.data;

import com.jolube.exam.model.Cliente;
import com.jolube.exam.model.Cuenta;
import com.jolube.exam.model.Movimiento;
import com.jolube.exam.model.TipoMovimiento;
import com.jolube.exam.repository.ClienteRepository;
import com.jolube.exam.repository.CuentaRepository;
import com.jolube.exam.repository.MovimientoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private CuentaRepository cuentaRepository;
    
    @Autowired
        private MovimientoRepository movimientoRepository;
    
    @Override
    public void run(String... args) throws Exception {
        cargarClientes();
        cargarCuentas();
        cargarMovimientosCuenta1();
        cargarMovimientosCuenta2();  
    }
    
    private void cargarClientes(){

        Cliente cliente1 = new Cliente();
        cliente1.setIdentificacion("123456789");
        cliente1.setNombre("Jose");
        cliente1.setApellido("Benavides");
        cliente1.setEdad(28);
        clienteRepository.save(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setIdentificacion("234567891");
        cliente2.setNombre("Daniel");
        cliente2.setApellido("ARcentales");
        cliente2.setEdad(29);
        clienteRepository.save(cliente2);

        Cliente cliente3 = new Cliente();
        cliente3.setIdentificacion("345678912");
        cliente3.setNombre("Juan");
        cliente3.setApellido("Perez");
        cliente3.setEdad(49);
        clienteRepository.save(cliente3);
    }
    
    private void cargarCuentas(){
        Cuenta cuenta1 = new Cuenta();
        cuenta1.setNumeroCuenta("123456789");
        cuenta1.setTipo("Ahorros");
        cuenta1.setFechaCreacion(new Date());
        cuenta1.setCliente(clienteRepository.findById("123456789").get());
        cuentaRepository.save(cuenta1);

        Cuenta cuenta2 = new Cuenta();
        cuenta2.setNumeroCuenta("234567891");
        cuenta2.setTipo("Corriente");
        cuenta2.setFechaCreacion(new Date());
        cuenta2.setCliente(clienteRepository.findById("234567891").get());
        cuentaRepository.save(cuenta2);
    }
    
    private void cargarMovimientosCuenta1(){
        Movimiento movimiento1 = new Movimiento();
        movimiento1.setId(1);
        movimiento1.setTipo(TipoMovimiento.CREDITO);
        movimiento1.setMonto(100);
        movimiento1.setDescripcion("Deposito inicial");
        movimiento1.setCuenta(cuentaRepository.findById("123456789").get());
        movimientoRepository.save(movimiento1);

        Movimiento movimiento2 = new Movimiento();
        movimiento2.setId(2);
        movimiento2.setTipo(TipoMovimiento.CREDITO);
        movimiento2.setMonto(50);
        movimiento2.setDescripcion("Deposito");
        movimiento2.setCuenta(cuentaRepository.findById("123456789").get());
        movimientoRepository.save(movimiento2);

        Movimiento movimiento3 = new Movimiento();
        movimiento3.setId(3);
        movimiento3.setTipo(TipoMovimiento.DEBITO);
        movimiento3.setMonto(25);
        movimiento3.setDescripcion("Retiro");
        movimiento3.setCuenta(cuentaRepository.findById("123456789").get());
        movimientoRepository.save(movimiento3);
    }

    private void cargarMovimientosCuenta2(){
        Movimiento movimiento1 = new Movimiento();
        movimiento1.setId(4);
        movimiento1.setTipo(TipoMovimiento.CREDITO);
        movimiento1.setMonto(100);
        movimiento1.setDescripcion("Deposito inicial");
        movimiento1.setCuenta(cuentaRepository.findById("234567891").get());
        movimientoRepository.save(movimiento1);
        

        Movimiento movimiento2 = new Movimiento();
        movimiento2.setId(5);
        movimiento2.setTipo(TipoMovimiento.DEBITO);
        movimiento2.setMonto(25);
        movimiento2.setDescripcion("Retiro");
        movimiento2.setCuenta(cuentaRepository.findById("234567891").get());
        movimientoRepository.save(movimiento2);
    }
}

