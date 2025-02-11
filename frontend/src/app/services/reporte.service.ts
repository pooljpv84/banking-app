import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reporte } from '../interfaces/reporte.interface';

@Injectable({
  providedIn: 'root'
})
export class ReporteService {
  private API_URL = 'http://localhost:8080/api/reportes/por-cedula';

  constructor(private http: HttpClient) {}

  obtenerReporte(cedula: string, fechaInicio: string, fechaFin: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.API_URL}?cedula=${cedula}&fechaInicio=${fechaInicio}&fechaFin=${fechaFin}`);
  }
}
