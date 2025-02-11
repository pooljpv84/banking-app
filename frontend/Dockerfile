# Etapa única: Construcción y ejecución de Angular
FROM node:18 AS development
WORKDIR /app

# Copiar package.json y package-lock.json antes para aprovechar la caché de dependencias
COPY package*.json ./

# Instalar dependencias
RUN npm install --legacy-peer-deps
RUN npm install jspdf jspdf-autotable file-saver

# Copiar todo el código fuente
COPY . .

# Exponer el puerto en el que se ejecutará Angular (4200 por defecto)
EXPOSE 4200

# Comando para ejecutar Angular en modo desarrollo
CMD ["npm", "start"]


