import {Component} from '@angular/core';
import {ReporteService} from '../../services/reporte.service';
import {Reporte} from '../../interfaces/reporte.interface';
import {saveAs} from 'file-saver';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';


@Component({
  selector: 'app-reporte',
  standalone: false,
  templateUrl: './reporte.component.html',
  styleUrls: ['./reporte.component.css']
})
export class ReporteComponent {
  reporteData: Reporte[] = [];
  reporteGenerado = false;

  constructor(private reporteService: ReporteService) { }

  generarReporte(cedula: string, fechaInicio: string, fechaFin: string): void {
    if (!cedula || !fechaInicio || !fechaFin) {
      alert("Por favor, complete todos los campos.");
      return;
    }

    this.reporteService.obtenerReporte(cedula, fechaInicio, fechaFin).subscribe(
      (data) => {
        this.reporteData = data;
        this.reporteGenerado = true;
      },
      (error) => {
        console.error("Error al obtener el reporte:", error);
        alert("No se encontraron resultados para los datos ingresados.");
      }
    );
  }

  descargarJSON(): void {
    const blob = new Blob([JSON.stringify(this.reporteData, null, 2)], { type: 'application/json' });
    saveAs(blob, 'reporte.json');
  }

  descargarPDF(): void {
    const doc = new jsPDF();
    doc.text('Reporte de Movimientos', 14, 10);

    autoTable(doc, {
      head: [['Fecha', 'Cliente', 'Número Cuenta', 'Tipo', 'Saldo Inicial', 'Estado', 'Movimiento', 'Saldo Disponible']],
      body: this.reporteData.map((reporte) => [
        reporte.fecha,
        reporte.cliente,
        reporte.numeroCuenta,
        reporte.tipo,
        reporte.saldoInicial,
        reporte.estado ? 'Activo' : 'Inactivo',
        reporte.movimiento < 0 ? `-${Math.abs(reporte.movimiento)}` : reporte.movimiento,
        reporte.saldoDisponible
      ])
    });

    doc.save('reporte.pdf');
  }
}
