package com.jolube.exam.repository;



import com.jolube.exam.model.Movimiento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
    
    List<Movimiento> findByCuentaNumeroCuenta(String numeroCuenta);
}
