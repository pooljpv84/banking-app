import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {CuentaRequest, CuentaResponse} from '../interfaces/cuenta.interface';

@Injectable({
  providedIn: 'root'
})
export class CuentaService {
  private API_URL = 'http://localhost:8080/api/cuentas';

  constructor(private http: HttpClient) {}

  getCuentas(): Observable<CuentaResponse[]> {
    return this.http.get<CuentaResponse[]>(`${this.API_URL}`);
  }

  agregarCuenta(cuenta: CuentaRequest): Observable<CuentaResponse> {
    return this.http.post<CuentaResponse>(`${this.API_URL}/add`, cuenta);
  }
}
