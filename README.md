Proyecto Nexos - Spring Boot CRUD

Este repositorio contiene un proyecto de Spring Boot para la gestión de empleados y departamentos.
Incluye un CRUD completo con una base de datos relacional.

Requisitos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

JDK 17

Maven

MySQL Server

Postman (Opcional, para pruebas de API)

Instalación:

  1. Clona el repositorio:

   git clone https://github.com/tu_usuario/nexos-crud.git
   cd nexos-crud

  2. Crea la base de datos en MySQL:

    Ejecuta el siguiente script SQL en tu servidor MySQL:

   CREATE DATABASE nexos_db;
   USE nexos_db;

   CREATE TABLE departamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
    );

   CREATE TABLE empleado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    cargo VARCHAR(100) NOT NULL,
    departamento_id INT,
    FOREIGN KEY (departamento_id) REFERENCES departamento(id)
    );


  3.Configura la conexión a la base de datos en src/main/resources/application.properties:

   spring.datasource.url=jdbc:mysql://localhost:3306/nexos_db
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

Compilación y ejecución

  1. Compila el proyecto con Maven:

     mvn clean install

  2. Ejecuta la aplicación:

     mvn spring-boot:run


Uso de la API

Endpoints disponibles

Empleado

GET /empleado/listar → Lista todos los empleados.

POST /empleado/guardar → Guarda un nuevo empleado.

PUT /empleado/actualizar/{id} → Actualiza un empleado existente.

DELETE /empleado/eliminar/{id} → Elimina un empleado.

Departamento

GET /departamento/listar → Lista todos los departamentos.

POST /departamento/guardar → Guarda un nuevo departamento.

Pruebas con Postman

Puedes importar la colección de Postman incluida en el repositorio (nexos.postman_collection.json).

Contribuciones

Si deseas contribuir, por favor haz un fork del repositorio y crea un pull request con tus cambios.

Licencia

Este proyecto se distribuye bajo la licencia MIT. Para más detalles, consulta el archivo LICENSE.



   

