package com.example.account.DAO;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "personas")
public class DAOPersona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String identificacion;
    private String direccion;
    private String telefono;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    private DAOCliente cliente;
}
