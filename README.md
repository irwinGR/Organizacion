
## Cocumento para replica del sistema
En una BD MySQL crear la siguinete estructura de tabla:

CREATE TABLE `SMART`.`ORGANIZACION` (
  `IDEXTERNO` varchar(255) PRIMARY KEY NOT NULL,
  `NOMBRE` varchar(255) NOT NULL,
  `DIRECCION` varchar(255) NOT NULL,
  `CODIGO` varchar(255) NOT NULL
)

## Ejecutar el servicio

Linea de comando:
* CMD ["java -jar prueba-tecnica-backend-0.0.1-SNAPSHOT.jar"]


## Ir al servicio de interface de documentación Swagger para el API de la prueba expuesta

* http://localhost:8080/swagger-ui.html



