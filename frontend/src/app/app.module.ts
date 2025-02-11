import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {FormsModule} from '@angular/forms';
import {SharedModule} from './shared/shared.module';
import {HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from './app-routing.module';
import {CommonModule} from '@angular/common';
import {ClientesModule} from './pages/clientes/clientes.module';
import {CuentasModule} from './pages/cuentas/cuentas.module';
import {MovimientosModule} from './pages/movimientos/movimientos.module';
import {ReporteComponent} from './pages/reporte/reporte.component';
import {ReporteModule} from './pages/reporte/reporte.module';


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    SharedModule,
    ClientesModule,
    CuentasModule,
    MovimientosModule,
    ReporteModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
