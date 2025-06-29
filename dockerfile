FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copia el script mvnw y el directorio .mvn
COPY . .

# Asegúrate de que mvnw sea ejecutable
RUN chmod +x mvnw

# Ahora ejecuta mvnw para compilar
RUN ./mvnw clean package

# Etapa de producción
FROM eclipse-temurin:17-jdk AS prod
WORKDIR /app
# Copia el JAR compilado de la etapa de construcción
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]