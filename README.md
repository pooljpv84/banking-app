# Spring y Angular con Docker 🐳

Este proyecto contiene un **backend en Spring Boot**, un **frontend en Angular** y una **base de datos MySQL** en Docker.

## 🚀 Tecnologías
- **Backend**: Spring Boot 3.4.2
- **Frontend**: Angular 19
- **Base de datos**: MySQL 8.0
- **Docker & Docker Compose**

## 📦 Estructura
```
global/
│── backend/                    # Código del backend en Spring Boot
│── frontend/                   # Código del frontend en Angular
│── db-init/BaseDatos.sql       # Archivo SQL para inicializar la base de datos
│── endPoints.json              # Archivo para ver los endPoints en Postman
│── reporte.json                # Ejemplo del reporte en JSON
│── reporte.pdf                 # Ejemplo del reporte en PDF
│── docker-compose.yml
│── .gitignore
│── README.md
```

## 🛠️ Construcción y Ejecución
Clonar el repositorio construir y ejecutar los servicios, usa el siguiente comando:

```sh
docker-compose up --build -d
```

Esto levantará los contenedores en segundo plano (`-d`).

## 📊 Servicios Levantados
Cuando el `docker-compose up` finalice, tendrás los siguientes servicios activos:

| Servicio | URL |
|----------|----|
| Backend | http://localhost:8080 |
| Frontend | http://localhost:4200 |
| MySQL | localhost:3306 |

## ⏳ Esperar a que los contenedores estén listos
Puedes verificar el estado de los contenedores con:
```sh
docker ps
```

## ✅ Verificar Conexión
Apis de Swagger (Back End):
```sh
http://localhost:8080/swagger-ui/index.html
```
Interfaz en Angular:
```sh
http://localhost:4200/clientes
```


## 👀 Autor
Creado por Paul Velasco