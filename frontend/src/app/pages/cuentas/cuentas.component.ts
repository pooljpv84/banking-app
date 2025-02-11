import { Component, OnInit } from '@angular/core';
import { CuentaService } from '../../services/cuenta.service';
import { CuentaRequest, CuentaResponse} from '../../interfaces/cuenta.interface';
import { NgForm } from '@angular/forms';
import {ClienteResponse} from '../../interfaces/cliente.interface';
import {ClienteService} from '../../services/cliente.service';

declare var bootstrap: any;

@Component({
  selector: 'app-cuentas',
  standalone: false,
  templateUrl: './cuentas.component.html',
  styleUrls: ['./cuentas.component.css']
})
export class CuentasComponent implements OnInit {
  cuentas: CuentaResponse[] = [];
  clientes: ClienteResponse[] = []; // Lista de clientes para el select

  nuevaCuenta: CuentaRequest = {
    numeroCuenta: '',
    tipoCuenta: 'AHORRO',
    saldoInicial: 0,
    estado: true,
    clienteId: 0
  };

  constructor(private cuentaService: CuentaService, private clienteService: ClienteService) {}

  ngOnInit(): void {
    this.obtenerCuentas();
    this.obtenerClientes(); // Cargar clientes al iniciar
  }

  obtenerCuentas(): void {
    this.cuentaService.getCuentas().subscribe(data => {
      this.cuentas = data;
    });
  }

  obtenerClientes(): void {
    this.clienteService.getClientes().subscribe(data => {
      this.clientes = data;
    });
  }

  abrirModal(): void {
    const modalElement = document.getElementById('nuevaCuentaModal');
    const modal = new bootstrap.Modal(modalElement);
    modal.show();
  }

  guardarCuenta(form: NgForm): void {
    if (form.invalid) {
      alert("Por favor, complete todos los campos obligatorios.");
      return;
    }

    this.cuentaService.agregarCuenta(this.nuevaCuenta).subscribe(response => {
      console.log('Cuenta agregada:', response);
      this.obtenerCuentas();

      // Cerrar modal
      const modalElement = document.getElementById('nuevaCuentaModal');
      const modal = bootstrap.Modal.getInstance(modalElement);
      modal.hide();

      // Reiniciar formulario
      this.nuevaCuenta = {
        numeroCuenta: '',
        tipoCuenta: 'AHORRO',
        saldoInicial: 0,
        estado: true,
        clienteId: 0
      };
      form.resetForm();
    });
  }
}
