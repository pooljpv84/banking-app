import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ClienteRequest, ClienteResponse} from '../interfaces/cliente.interface';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private API_URL = 'http://localhost:8080/api/clientes';

  constructor(private http: HttpClient) {
  }

  getClientes(): Observable<ClienteResponse[]> {
    return this.http.get<ClienteResponse[]>(`${this.API_URL}`);
  }

  agregarCliente(cliente: ClienteRequest): Observable<ClienteResponse> {
    return this.http.post<ClienteResponse>(`${this.API_URL}/add`, cliente);
  }

  actualizarCliente(id: number, cliente: ClienteRequest): Observable<ClienteResponse> {
    return this.http.put<ClienteResponse>(`${this.API_URL}/update/${id}`, cliente);
  }

  eliminarCliente(id: number): Observable<string> {
    return this.http.delete<string>(`${this.API_URL}/delete/${id}`, { responseType: 'text' as 'json' });
  }


}
