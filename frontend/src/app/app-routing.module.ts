import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientesComponent } from './pages/clientes/clientes.component';
import { CuentasComponent } from './pages/cuentas/cuentas.component';
import {MovimientosComponent} from './pages/movimientos/movimientos.component';
import {ReporteComponent} from './pages/reporte/reporte.component';

const routes: Routes = [
  { path: 'clientes', component: ClientesComponent },
  { path: 'cuentas', component: CuentasComponent },
  { path: 'movimientos', component: MovimientosComponent },
  { path: 'reportes', component: ReporteComponent },
  { path: '', redirectTo: '/clientes', pathMatch: 'full' },
  { path: '**', redirectTo: '/clientes' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
