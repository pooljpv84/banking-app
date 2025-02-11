export interface Movimiento {
  id: number;
  numeroCuenta: string;
  estado: boolean;
  saldoInicial: number;
  tipoCuenta: string;
  movimiento: string;
}

export interface MovimientoRequest {
  valor: number;
  numeroCuenta: string;
  tipoMovimiento: "DEPOSITO" | "RETIRO";
}

export interface MovimientoResponse {
  id: number;
  fecha: string;
  saldoDisponible: number;
  valor: number;
  numeroCuenta: string;
  tipoMovimiento: string;
  clienteNombre: string;
  estado: boolean;
  saldoInicial: number;
}
