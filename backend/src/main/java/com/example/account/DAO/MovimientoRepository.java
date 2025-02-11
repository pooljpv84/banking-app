package com.example.account.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface MovimientoRepository extends JpaRepository<DAOMovimiento, Long> {
    List<DAOMovimiento> findByCuenta_NumeroCuenta(String numeroCuenta);
    List<DAOMovimiento> findByCuenta_NumeroCuentaAndFechaBetween(
            String numeroCuenta, Date fechaInicio, Date fechaFin
    );
    List<DAOMovimiento> findByCuenta_Cliente_Persona_IdentificacionAndFechaBetween(String cedula, Date fechaInicio, Date fechaFin);
}
