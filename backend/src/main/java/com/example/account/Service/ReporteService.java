package com.example.account.Service;

import com.example.account.DAO.DAOMovimiento;
import com.example.account.DAO.MovimientoRepository;
import com.example.account.DTO.DTOReporte;
import com.example.account.DTO.DTOReporteByCedula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    public List<DTOReporte> obtenerReporte(String numeroCuenta, Date fechaInicio, Date fechaFin) {
        // Ajustar las fechas de inicio y fin
        fechaInicio = ajustarFechaInicio(fechaInicio);
        fechaFin = ajustarFechaFin(fechaFin);

        List<DAOMovimiento> movimientos = movimientoRepository.findByCuenta_NumeroCuentaAndFechaBetween(numeroCuenta, fechaInicio, fechaFin);

        return movimientos.stream().map(mov -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fechaFormateada = sdf.format(mov.getFecha());

            // Si es RETIRO, convertir el valor en negativo
            Float movimientoValor = mov.getTipoMovimiento().toString().equals("RETIRO") ? -mov.getValor() : mov.getValor();

            return new DTOReporte(
                    fechaFormateada,
                    mov.getCuenta().getCliente().getPersona().getNombre(),
                    mov.getCuenta().getNumeroCuenta(),
                    mov.getCuenta().getTipoCuenta().toString(),
                    mov.getCuenta().getSaldoInicial(),
                    mov.getCuenta().getEstado(),
                    movimientoValor,
                    mov.getSaldoDisponible(),
                    mov.getTipoMovimiento().toString()
            );
        }).collect(Collectors.toList());
    }

    public List<DTOReporteByCedula> obtenerReportePorCedula(String cedula, Date fechaInicio, Date fechaFin) {
        // Ajustar las fechas de inicio y fin
        fechaInicio = ajustarFechaInicio(fechaInicio);
        fechaFin = ajustarFechaFin(fechaFin);

        List<DAOMovimiento> movimientos = movimientoRepository.findByCuenta_Cliente_Persona_IdentificacionAndFechaBetween(cedula, fechaInicio, fechaFin);

        return movimientos.stream().map(mov -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fechaFormateada = sdf.format(mov.getFecha());

            // Si es RETIRO, convertir el valor en negativo
            Float movimientoValor = mov.getTipoMovimiento().toString().equals("RETIRO") ? -mov.getValor() : mov.getValor();

            return new DTOReporteByCedula(
                    fechaFormateada,
                    mov.getCuenta().getCliente().getPersona().getNombre(),
                    mov.getCuenta().getNumeroCuenta(),
                    mov.getCuenta().getTipoCuenta().toString(),
                    mov.getCuenta().getSaldoInicial(),
                    mov.getCuenta().getEstado(),
                    movimientoValor,
                    mov.getSaldoDisponible(),
                    mov.getTipoMovimiento().toString()
            );
        }).collect(Collectors.toList());
    }

    private Date ajustarFechaInicio(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    private Date ajustarFechaFin(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }
}
