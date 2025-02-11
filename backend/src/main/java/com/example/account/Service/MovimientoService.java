package com.example.account.Service;

import com.example.account.DAO.CuentaRepository;
import com.example.account.DAO.DAOCuenta;
import com.example.account.DAO.DAOMovimiento;
import com.example.account.DAO.MovimientoRepository;

import com.example.account.DTO.DTOMovimiento;

import com.example.account.DTO.Enum.TipoMovimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimientoService {
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public DTOMovimiento registrarMovimiento(DTOMovimiento dtoMovimiento) {
        // Buscar la cuenta en la base de datos
        DAOCuenta daoCuenta = cuentaRepository.findByNumeroCuenta(dtoMovimiento.getNumeroCuenta())
                .orElse(null);

        if (daoCuenta == null) {
            return new DTOMovimiento("Cuenta no encontrada con número: " + dtoMovimiento.getNumeroCuenta());
        }

        // Obtener el último movimiento para calcular el saldo disponible
        List<DAOMovimiento> movimientosPrevios = movimientoRepository.findByCuenta_NumeroCuenta(daoCuenta.getNumeroCuenta());
        Float saldoDisponible = movimientosPrevios.isEmpty() ? daoCuenta.getSaldoInicial() :
                movimientosPrevios.get(movimientosPrevios.size() - 1).getSaldoDisponible();

        // Validar si el movimiento es un RETIRO y hay saldo suficiente
        if ("RETIRO".equalsIgnoreCase(String.valueOf(dtoMovimiento.getTipoMovimiento())) && saldoDisponible < dtoMovimiento.getValor()) {
            return new DTOMovimiento("Saldo insuficiente para realizar la transacción.");
        }

        // Calcular el nuevo saldo disponible
        if ("DEPOSITO".equalsIgnoreCase(String.valueOf(dtoMovimiento.getTipoMovimiento()))) {
            saldoDisponible += dtoMovimiento.getValor();
        } else if ("RETIRO".equalsIgnoreCase(String.valueOf(dtoMovimiento.getTipoMovimiento()))) {
            saldoDisponible -= dtoMovimiento.getValor();
        } else {
            return new DTOMovimiento("Tipo de movimiento inválido.");
        }

        // Crear el nuevo movimiento
        DAOMovimiento daoMovimiento = new DAOMovimiento();
        daoMovimiento.setFecha(new Date());
        daoMovimiento.setTipoMovimiento(TipoMovimiento.valueOf(dtoMovimiento.getTipoMovimiento().toString()));
        daoMovimiento.setValor(dtoMovimiento.getValor());
        daoMovimiento.setSaldoDisponible(saldoDisponible); // Se usa saldoDisponible
        daoMovimiento.setCuenta(daoCuenta);

        movimientoRepository.save(daoMovimiento);

        return convertirToDTO(daoMovimiento);
    }

    public List<DTOMovimiento> getMovimientosByCuentaId(String cuentaId) {
        return movimientoRepository.findByCuenta_NumeroCuenta(cuentaId).stream()
                .map(this::convertirToDTO)
                .collect(Collectors.toList());
    }

    public List<DTOMovimiento> getTodosLosMovimientos() {
        return movimientoRepository.findAll().stream()
                .map(this::convertirToDTO)
                .collect(Collectors.toList());
    }

    public void eliminarMovimiento(Long movimientoId) {
        if (!movimientoRepository.existsById(movimientoId)) {
            throw new RuntimeException("Movimiento no encontrado con ID: " + movimientoId);
        }
        movimientoRepository.deleteById(movimientoId);
    }

    private DTOMovimiento convertirToDTO(DAOMovimiento daoMovimiento) {
        DTOMovimiento dtoMovimiento = new DTOMovimiento();
        dtoMovimiento.setId(daoMovimiento.getId());
        dtoMovimiento.setFecha(daoMovimiento.getFecha());
        dtoMovimiento.setSaldoDisponible(daoMovimiento.getSaldoDisponible());
        dtoMovimiento.setValor(daoMovimiento.getValor());
        dtoMovimiento.setTipoMovimiento(TipoMovimiento.valueOf(daoMovimiento.getTipoMovimiento().toString()));
        dtoMovimiento.setNumeroCuenta(daoMovimiento.getCuenta().getNumeroCuenta());
        dtoMovimiento.setClienteNombre(daoMovimiento.getCuenta().getCliente().getPersona().getNombre());
        dtoMovimiento.setEstado(daoMovimiento.getCuenta().getEstado());
        dtoMovimiento.setSaldoInicial(daoMovimiento.getCuenta().getSaldoInicial());
        return dtoMovimiento;
    }

    public List<DTOMovimiento> getAllMovimientos() {
        List<DAOMovimiento> movimientos = movimientoRepository.findAll();
        return movimientos.stream().map(this::convertirToDTOPersonalizado).collect(Collectors.toList());
    }

    private DTOMovimiento convertirToDTOPersonalizado(DAOMovimiento movimiento) {
        DTOMovimiento dto = new DTOMovimiento();
        dto.setId(movimiento.getId());
        dto.setNumeroCuenta(movimiento.getCuenta().getNumeroCuenta());
        dto.setTipoCuenta(movimiento.getCuenta().getTipoCuenta());
        dto.setSaldoInicial(movimiento.getCuenta().getSaldoInicial());
        dto.setEstado(movimiento.getCuenta().getEstado());
        dto.setMovimiento(movimiento.getTipoMovimiento() + " de " + movimiento.getValor());
        return dto;
    }
}
