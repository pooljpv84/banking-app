import {Component, OnInit} from '@angular/core';
import {ClienteService} from '../../services/cliente.service';
import {ClienteResponse, ClienteRequest} from '../../interfaces/cliente.interface';
import {NgForm} from '@angular/forms';

declare var bootstrap: any;

@Component({
  selector: 'app-clientes',
  standalone: false,
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent implements OnInit {
  clientes: ClienteResponse[] = [];
  nuevoCliente: ClienteRequest = {
    persona: {
      nombre: '',
      identificacion: '',
      direccion: '',
      telefono: ''
    },
    edad: 0,
    genero: 'Masculino',
    password: '',
    estado: true
  };
  clienteSeleccionado: ClienteResponse | null = null;

  constructor(private clienteService: ClienteService) {
  }

  ngOnInit(): void {
    this.obtenerClientes();
  }

  obtenerClientes(): void {
    this.clienteService.getClientes().subscribe(data => {
      this.clientes = data;
    });
  }

  abrirModal(): void {
    const modalElement = document.getElementById('nuevoClienteModal');
    const modal = new bootstrap.Modal(modalElement);
    modal.show();
  }

  guardarCliente(form: NgForm): void {
    if (form.invalid) {
      alert("Por favor, complete todos los campos obligatorios.");
      return;
    }

    this.clienteService.agregarCliente(this.nuevoCliente).subscribe(response => {
      this.obtenerClientes();
      const modalElement = document.getElementById('nuevoClienteModal');
      const modal = bootstrap.Modal.getInstance(modalElement);
      modal.hide();
      this.nuevoCliente = {
        persona: {
          nombre: '',
          identificacion: '',
          direccion: '',
          telefono: ''
        },
        edad: 0,
        genero: 'Masculino',
        password: '',
        estado: true
      };
      form.resetForm();
    });
  }

  abrirModalEditar(cliente: ClienteResponse): void {
    this.clienteSeleccionado = {...cliente};
    const modalElement = document.getElementById('editarClienteModal');
    const modal = new bootstrap.Modal(modalElement);
    modal.show();
  }

  actualizarCliente(): void {
    if (!this.clienteSeleccionado?.id) {
      console.error("No se puede actualizar: Cliente sin ID");
      return;
    }

    this.clienteService.actualizarCliente(this.clienteSeleccionado.id, this.clienteSeleccionado).subscribe(response => {
      this.obtenerClientes();
      const modalElement = document.getElementById('editarClienteModal');
      const modal = bootstrap.Modal.getInstance(modalElement);
      modal.hide();
      this.clienteSeleccionado = null;
    });
  }

  eliminarCliente(id: number): void {
    if (confirm('¿Estás seguro de eliminar este cliente?')) {
      this.clienteService.eliminarCliente(id).subscribe({
        next: (response: any) => {
          if (typeof response === 'string') {
            if (response.includes("Cliente eliminado con éxito")) {
              console.log(`Cliente con ID ${id} eliminado`);
              this.obtenerClientes(); // Recarga la lista de clientes
            } else if (response.includes("No se puede eliminar el cliente porque tiene cuentas asociadas.")) {
              alert("No se puede eliminar el cliente porque tiene cuentas asociadas.");
            } else if (response.includes("Cliente no encontrado.")) {
              alert("El cliente no existe en la base de datos.");
            }
          }
        },
        error: (error) => {
          console.error("Error al eliminar cliente", error);
          alert("Ocurrió un error inesperado al eliminar el cliente.");
        }
      });
    }
  }






}
