package com.jolube.exam.repository;

import com.jolube.exam.model.Cuenta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, String> {
    
    Optional<Cuenta> findByClienteIdentificacion(String clienteId);
}
