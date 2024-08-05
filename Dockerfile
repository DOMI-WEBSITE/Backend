# Importar JDK y copiar los archivos necesarios
FROM openjdk:17-jdk AS build
WORKDIR /app
COPY pom.xml .
COPY src src

# Copiar contenedor Maven
COPY mvnw .
COPY .mvn .mvn

# Establecer permiso de ejecución para el contenedor Maven
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Etapa 2: cree la imagen final de Docker usando OpenJDK 17
FROM openjdk:17-jdk
VOLUME /tmp

# Copie el JAR de la etapa de construcción
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8095