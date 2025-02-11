import {Component, OnInit} from '@angular/core';
import {MovimientoService} from '../../services/movimiento.service';
import {Movimiento, MovimientoRequest} from '../../interfaces/movimiento.interface';
import {NgForm} from '@angular/forms';

declare var bootstrap: any;

@Component({
  selector: 'app-movimientos',
  standalone: false,
  templateUrl: './movimientos.component.html',
  styleUrls: ['./movimientos.component.css']
})
export class MovimientosComponent implements OnInit {
  movimientos: Movimiento[] = [];
  nuevoMovimiento: MovimientoRequest = {
    valor: 0,
    numeroCuenta: '',
    tipoMovimiento: 'DEPOSITO'
  };

  constructor(private movimientoService: MovimientoService) {
  }

  ngOnInit(): void {
    this.obtenerMovimientos();
  }

  obtenerMovimientos(): void {
    this.movimientoService.getMovimientos().subscribe(data => {
      this.movimientos = data;
    });
  }

  abrirModal(): void {
    const modalElement = document.getElementById('nuevoMovimientoModal');
    const modal = new bootstrap.Modal(modalElement);
    modal.show();
  }

  guardarMovimiento(form: NgForm): void {
    if (form.invalid) {
      alert('Por favor, complete todos los campos correctamente.');
      return;
    }

    this.movimientoService.agregarMovimiento(this.nuevoMovimiento).subscribe(response => {
      if (response && response.errorMessage) {
        // Si el backend devuelve un error con status 200
        alert(response.errorMessage);
        return;
      }

      console.log('Movimiento agregado:', response);
      this.obtenerMovimientos(); // Refrescar la tabla

      // Cerrar el modal
      const modalElement = document.getElementById('nuevoMovimientoModal');
      const modal = bootstrap.Modal.getInstance(modalElement);
      modal.hide();

      // Resetear formulario
      this.nuevoMovimiento = {
        valor: 0,
        numeroCuenta: '',
        tipoMovimiento: 'DEPOSITO'
      };
      form.resetForm();
    }, error => {
      console.error("Error al agregar movimiento", error);
      alert("Ocurrió un error inesperado. Intente nuevamente.");
    });
  }
}
