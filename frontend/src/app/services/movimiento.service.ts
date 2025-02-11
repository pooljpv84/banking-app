import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Movimiento, MovimientoRequest } from '../interfaces/movimiento.interface';

@Injectable({
  providedIn: 'root'
})
export class MovimientoService {
  private API_URL = 'http://localhost:8080/api/movimientos';

  constructor(private http: HttpClient) {}

  getMovimientos(): Observable<Movimiento[]> {
    return this.http.get<Movimiento[]>(`${this.API_URL}`);
  }

  agregarMovimiento(movimiento: MovimientoRequest): Observable<any> {
    return this.http.post<any>(`${this.API_URL}/add`, movimiento);
  }
}
