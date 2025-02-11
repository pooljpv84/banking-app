package com.example.account.Controller;

import com.example.account.DTO.DTOCliente;
import com.example.account.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("*")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;          //OBJ del servicio

    @GetMapping
    public List<DTOCliente> getClientes() {
        return clienteService.getClientes();
    }

    @GetMapping("/{clienteId}")
    public DTOCliente obtenerCliente(@PathVariable Long clienteId) {
        return clienteService.getClienteById(clienteId);
    }

    @PostMapping("/add")
    public ResponseEntity<DTOCliente> registrarCliente(@RequestBody DTOCliente dtoCliente) throws URISyntaxException {
        DTOCliente nuevoCliente = clienteService.registrarCliente(dtoCliente);
        return ResponseEntity.created(new URI("/api/clientes/" + nuevoCliente.getId())).body(nuevoCliente);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<DTOCliente> updateCliente(@PathVariable Long id, @RequestBody DTOCliente dtoCliente) {
        DTOCliente updatedCliente = clienteService.updateCliente(id, dtoCliente);
        return ResponseEntity.ok(updatedCliente);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
        try {
            clienteService.deleteCliente(id);
            return ResponseEntity.ok("Cliente eliminado con éxito.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
