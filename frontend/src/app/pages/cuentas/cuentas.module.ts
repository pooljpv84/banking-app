import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CuentasComponent } from './cuentas.component';
import { CuentaService } from '../../services/cuenta.service';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    CuentasComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
  ],
  providers: [CuentaService],
  exports: [CuentasComponent],
})
export class CuentasModule {}
