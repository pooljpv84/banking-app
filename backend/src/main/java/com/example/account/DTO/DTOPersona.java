package com.example.account.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOPersona {
    private Long id;
    private String nombre;
    private String identificacion;
    private String direccion;
    private String telefono;
}
