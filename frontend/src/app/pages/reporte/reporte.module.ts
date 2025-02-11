import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReporteComponent} from './reporte.component';
import {ReporteService} from '../../services/reporte.service';
import {FormsModule} from '@angular/forms';


@NgModule({
  declarations: [
    ReporteComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
  ],
  providers: [ReporteService],
  exports: [ReporteComponent],
})
export class ReporteModule {
}
