# Microservicio: Gestion de Cuentas-Movimientos

Este microservicio forma parte de la solucion de la prueba tecnica backend java. Se encarga de la creacion de cuentas bancarias, el registro de depositos y retiros, generacion de reportes por fecha.

## Tecnologias
- Java 17
- Spring Boot
- Maven
- VS Code (opcional)
  - **Java Extension Pack**
  - **Spring Boot Tools**
  - **Lombok Annotations Support**
- H2
- Postman

## Como Ejecutar en local
1. Clonar el repositorio
2. Ejecutar con Maven:
   -  -/mvnw spring-boot:run (Terminal)
3. Acceder a la API REST en:
   -  http://localhost:8080/clientes
4. Acceder a la consola de la base de datos en memoria (H2):
   - http://localhost:8081/h2-console
     - JDBC URL: jdbc:h2:mem:testdb
     - User: sa
     - password:
## Pruebas Automatizadas
./mvnw test
