package com.example.account.Service;

import com.example.account.DAO.*;
import com.example.account.DTO.DTOCliente;
import com.example.account.DTO.DTOCuenta;
import com.example.account.DTO.DTOMovimiento;
import com.example.account.DTO.Enum.TipoCuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CuentaService {
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public List<DTOCuenta> getCuentas() {
        return cuentaRepository.findAll().stream()
                .map(this::convertirToDTO)
                .collect(Collectors.toList());
    }

    public List<DTOCuenta> getCuentasByCliente(Long clienteId) {
        // Verificar si el cliente existe
        if (!clienteRepository.existsById(clienteId)) {
            throw new RuntimeException("Cliente no encontrado con ID: " + clienteId);
        }

        return cuentaRepository.findByCliente_Id(clienteId).stream()
                .map(this::convertirToDTO)
                .collect(Collectors.toList());
    }

    public DTOCuenta getCuentaById(Long cuentaId) {
        Optional<DAOCuenta> cuenta = cuentaRepository.findById(cuentaId);
        return cuenta.map(this::convertirToDTO).orElse(null);
    }

    public DTOCuenta crearCuenta(DTOCuenta dtoCuenta) {
        // Buscar el cliente en la BDD
        DAOCliente daoCliente = clienteRepository.findById(dtoCuenta.getClienteId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente no encontrado con ID: " + dtoCuenta.getClienteId()
                ));

        // Crear la cuenta
        DAOCuenta daoCuenta = new DAOCuenta();
        daoCuenta.setNumeroCuenta(dtoCuenta.getNumeroCuenta());
        daoCuenta.setTipoCuenta(TipoCuenta.valueOf(dtoCuenta.getTipoCuenta()));
        daoCuenta.setSaldoInicial(dtoCuenta.getSaldoInicial());
        daoCuenta.setEstado(dtoCuenta.getEstado());
        daoCuenta.setCliente(daoCliente);  // Asignar el cliente encontrado

        cuentaRepository.save(daoCuenta);
        return convertirToDTO(daoCuenta);
    }

    private DTOCuenta convertirToDTO(DAOCuenta daoCuenta) {
        DTOCuenta dtoCuenta = new DTOCuenta();
        dtoCuenta.setId(daoCuenta.getId());
        dtoCuenta.setNumeroCuenta(daoCuenta.getNumeroCuenta());
        dtoCuenta.setTipoCuenta(String.valueOf(daoCuenta.getTipoCuenta()));
        dtoCuenta.setSaldoInicial(daoCuenta.getSaldoInicial());
        dtoCuenta.setEstado(daoCuenta.getEstado());
        dtoCuenta.setClienteId(daoCuenta.getCliente().getId()); // Mostrar el ID del cliente

        if (daoCuenta.getCliente().getPersona() != null) {
            dtoCuenta.setClienteNombre(daoCuenta.getCliente().getPersona().getNombre());
        }

        return dtoCuenta;
    }
}
