package com.example.account.Controller;

import com.example.account.DTO.DTOMovimiento;
import com.example.account.Service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@CrossOrigin("*")
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;

    @PostMapping("/add")
    public ResponseEntity<DTOMovimiento> registrarMovimiento(@RequestBody DTOMovimiento dtoMovimiento) {
        DTOMovimiento nuevoMovimiento = movimientoService.registrarMovimiento(dtoMovimiento);
        return ResponseEntity.ok(nuevoMovimiento);
    }

    @GetMapping("/cuenta/{numeroCuenta}")
    public List<DTOMovimiento> getMovimientosPorCuenta(@PathVariable String numeroCuenta) {
        return movimientoService.getMovimientosByCuentaId(numeroCuenta);
    }
    @GetMapping
    public ResponseEntity<List<DTOMovimiento>> getAllMovimientos() {
        List<DTOMovimiento> movimientos = movimientoService.getAllMovimientos();
        return ResponseEntity.ok(movimientos);
    }
}
