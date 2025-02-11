package com.example.account.DAO;

import com.example.account.DTO.Enum.TipoMovimiento;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "movimientos")
public class DAOMovimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    private Float saldoDisponible;
    private Float valor;

    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", referencedColumnName = "id")
    private DAOCuenta cuenta;
}
