package com.example.account.DTO;

import com.example.account.DTO.Enum.TipoCuenta;
import com.example.account.DTO.Enum.TipoMovimiento;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DTOMovimiento {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)   //solo en resp / no en el body
    private Date fecha;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)

    private Float saldoDisponible;
    private Float valor;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long cuentaId;

    private String numeroCuenta;


    private TipoMovimiento tipoMovimiento;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String clienteNombre;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean estado;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Float saldoInicial;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String errorMessage;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private TipoCuenta tipoCuenta;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String movimiento;

    public DTOMovimiento() {
    }

    public DTOMovimiento(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}