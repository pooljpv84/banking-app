package com.example.account.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DTOCuenta {
    private Long id;
    private String numeroCuenta;
    private String tipoCuenta;
    private Float saldoInicial;
    private Boolean estado;
    private Long clienteId;
    private String clienteNombre;

    //private List<DTOMovimiento> movimientos;

    public DTOCuenta(Long id, String numeroCuenta, String tipoCuenta, Float saldoInicial, Boolean estado, Long clienteId, String clienteNombre) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.clienteId = clienteId;
        this.clienteNombre = clienteNombre;
    }

    public DTOCuenta() {
    }

}