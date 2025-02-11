package com.example.account.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<DAOCuenta, Long> {
    Optional<DAOCuenta> findByNumeroCuenta(String numeroCuenta);

    List<DAOCuenta> findByCliente_Id(Long clienteId);

}