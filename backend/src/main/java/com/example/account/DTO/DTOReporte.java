package com.example.account.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOReporte {
    private String fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private Float saldoInicial;
    private Boolean estado;
    private Float movimiento;
    private Float saldoDisponible;
    private String tipoMovimiento;

    public DTOReporte() {
    }

    public DTOReporte(String fecha, String cliente, String numeroCuenta, String tipo, Float saldoInicial, Boolean estado, Float movimiento, Float saldoDisponible, String tipoMovimiento) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.numeroCuenta = numeroCuenta;
        this.tipo = tipo;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.movimiento = movimiento;
        this.saldoDisponible = saldoDisponible;
        this.tipoMovimiento = tipoMovimiento;
    }
}
