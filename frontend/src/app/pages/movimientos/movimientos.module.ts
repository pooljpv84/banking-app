import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MovimientosComponent} from './movimientos.component';
import {MovimientoService} from '../../services/movimiento.service';
import {FormsModule} from '@angular/forms';


@NgModule({
  declarations: [
    MovimientosComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
  ],
  providers: [MovimientoService],
  exports: [MovimientosComponent],
})
export class MovimientosModule {
}
