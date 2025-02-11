// Interfaz para mostrar clientes obtenidos del backend
export interface ClienteResponse {
  id: number;
  persona: Persona;
  edad: number;
  genero: string;
  password: string;
  estado: boolean;
}

// Interfaz de Persona (se usa dentro de ClienteResponse)
export interface Persona {
  id: number;
  nombre: string;
  identificacion: string;
  direccion: string;
  telefono: string;
}

// Interfaz para enviar datos al backend al crear o actualizar un cliente
export interface ClienteRequest {
  persona: {
    nombre: string;
    identificacion: string;
    direccion: string;
    telefono: string;
  };
  edad: number;
  genero: string;
  password: string;
  estado: boolean;
}
