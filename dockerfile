FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copia primero los archivos del Maven Wrapper
COPY mvnw .
COPY .mvn/ .mvn/
COPY pom.xml .

# Copia el código fuente (esto es opcional aquí, podrías hacerlo después de descargar dependencias)
COPY src src

# Asegúrate de que mvnw sea ejecutable
RUN chmod +x mvnw

# Descarga las dependencias primero (esto crea una capa caché)
RUN ./mvnw dependency:go-offline

# Ahora compila el proyecto
RUN ./mvnw clean package

# Etapa de producción
FROM eclipse-temurin:17-jdk AS prod
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]