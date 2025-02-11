package com.example.account.DTO;

import com.example.account.DTO.Enum.TipoMovimiento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DTOReporteByCedula
{
    private String fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private Float saldoInicial;
    private Boolean estado;
    private Float movimiento;
    private Float saldoDisponible;
    private String tipoMovimiento;
}
