import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientesComponent } from './clientes.component';
import { ClienteService } from '../../services/cliente.service';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    ClientesComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
  ],
  providers: [ClienteService],
  exports: [ClientesComponent],
})
export class ClientesModule {}
