# Usar imagen base de OpenJDK 17
FROM eclipse-temurin:17-jdk-alpine

# Crear directorio para la app
WORKDIR /app

# Copiar el jar generado desde target/
COPY target/tu-app-springboot.jar app.jar

# Exponer el puerto que usa la app
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java","-jar","app.jar"]