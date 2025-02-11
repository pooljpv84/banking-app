package com.example.account.Controller;

import com.example.account.DAO.ClienteRepository;
import com.example.account.DTO.DTOCliente;
import com.example.account.DTO.DTOCuenta;
import com.example.account.Service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
@CrossOrigin("*")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public List<DTOCuenta> getCuentas() {
        return cuentaService.getCuentas();
    }

    @GetMapping("/cliente/{clienteId}")
    public List<DTOCuenta> getCuentasPorCliente(@PathVariable Long clienteId) {
        return cuentaService.getCuentasByCliente(clienteId);
    }
    @PostMapping("/add")
    public ResponseEntity<DTOCuenta> crearCuenta(@RequestBody DTOCuenta dtoCuenta) throws URISyntaxException {
        DTOCuenta nuevaCuenta = cuentaService.crearCuenta(dtoCuenta);
        return ResponseEntity.created(new URI("/api/cuentas/" + nuevaCuenta.getId())).body(nuevaCuenta);
    }
}
