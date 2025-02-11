package com.example.account.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DTOCliente {
    private Long id;
    private DTOPersona persona;
    private Integer edad;
    private String genero;
    private String password;
    private Boolean estado;
    //private List<DTOCuenta> cuentas;

    public DTOCliente(Long id, DTOPersona persona, Integer edad, String genero, String password, Boolean estado) {
        this.id = id;
        this.persona = persona;
        this.edad = edad;
        this.genero = genero;
        this.password = password;
        this.estado = estado;
    }

    public DTOCliente() {
    }
}
