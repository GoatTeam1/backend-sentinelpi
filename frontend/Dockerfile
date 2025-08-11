FROM node:22-bookworm-slim

ENV TZ=America/Mexico_City
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Carpeta de trabajo
WORKDIR /usr/src/app

# Instala deps primero (cache de capas)
COPY package*.json ./
RUN npm ci --no-audit --no-fund

# Variables para que el watcher funcione bien dentro de Docker/Windows
ENV CHOKIDAR_USEPOLLING=true
ENV WATCHPACK_POLLING=true
ENV NODE_OPTIONS="--max_old_space_size=4096"

# Expone el puerto del dev server
EXPOSE 4200

# Comando: arrancar Angular escuchando en 0.0.0.0
CMD ["npm", "run", "start", "--", "--host", "0.0.0.0", "--port", "4200"]