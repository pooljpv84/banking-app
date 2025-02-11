package com.example.account.Controller;

import com.example.account.DTO.DTOReporte;
import com.example.account.DTO.DTOReporteByCedula;
import com.example.account.Service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin("*")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public List<DTOReporte> obtenerReporte(
            @RequestParam String numeroCuenta,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {

        return reporteService.obtenerReporte(numeroCuenta, fechaInicio, fechaFin);
    }

    @GetMapping("/por-cedula")
    public List<DTOReporteByCedula> obtenerReportePorCedula(
            @RequestParam String cedula,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin
    ) {
        return reporteService.obtenerReportePorCedula(cedula, fechaInicio, fechaFin);
    }
}
