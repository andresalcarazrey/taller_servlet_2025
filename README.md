# Proyecto WebApp Taller basado en el examen Taller visto en clase. Con Maven y Tomcat 9

Este proyecto es una aplicación web desarrollada con Maven y desplegada en un servidor Tomcat 9. 
La aplicación permite gestionar clientes del taller a través de un servlet que maneja solicitudes HTTP.
De manera similar a lo realizado para los clientes, podéis agregar más código (más servlets, repositorios, ampliando el BBDDController)
para así gestionar los datos de las demás clases del modelo: Mecánico, TrabajoTaller, Vehiculos (Motos, Coches, Furgón), además de añadir todo
el crud de Clientes (falta el borrado de un cliente o la modificación mediante update)

## Estructura del Proyecto

El proyecto sigue una estructura estándar de Maven para aplicaciones web:

mi-proyecto-webapp/
├── src/

│   ├── main/

│   │   ├── java/

│   │   │   └── com/

│   │   │       └── politecnicomalaga/

│   │   │           └── tallerservlet/

│   │   │               ├── TallerServlet.java

│   │   │               ├── data/

│   │   │               │   ├── BBDDController.java

│   │   │               │   └── RepositorioClientes.java

│   │   │               └── model/

│   │   │                   └── Cliente.java

│   │   ├── resources/

│   │   └── webapp/

│   │       ├── WEB-INF/

│   │       │   └── web.xml

│   │       └── index.jsp

│   └── test/

│       └── java/

└── pom.xml


## Clases Principales

### `TallerServletClientes.java`

Esta clase es un servlet que maneja las solicitudes HTTP para gestionar clientes. 
Utiliza la biblioteca Apache Commons FileUpload para manejar datos de formularios multiparte.

- **Métodos Principales**:
  - `doGet`: Maneja las solicitudes GET.
  - `doPost`: Maneja las solicitudes POST.
  - `trabajoPrincipal`: Método principal que procesa las solicitudes.
  - `insertarClienteForm`: Inserta un cliente a partir de datos de formulario.
  - `insertarClienteJSON`: Inserta un cliente a partir de datos JSON.
  - `listarClientes`: Lista todos los clientes.

### `data/BBDDController.java`

Esta clase se encarga de la interacción con la base de datos.

- **Métodos Principales**:
  - `insertarMapeable`: Inserta un objeto mapeable en la base de datos.
  - `lastSQL`: Almacena la última consulta SQL ejecutada.

### `data/RepositorioClientes.java`

Esta clase proporciona métodos para interactuar con la base de datos y obtener información sobre los clientes.
Es un "controlador" entre la BBDD y el Servlet, para desacoplar el modelo y su mapeo a la BBDD de las peticiones
del servlet HTTP

- **Métodos Principales**:
  - `selectAllClientes`: Selecciona todos los clientes de la base de datos.
  - `insertCliente`: Inserta un objeto cliente en la BBDD

### `model/Cliente.java`

Esta clase representa un cliente en el sistema.

- **Atributos**:
  - `dni`: DNI del cliente.
  - `nombre`: Nombre del cliente.
  - `apellido1`: Primer apellido del cliente.
  - `apellido2`: Segundo apellido del cliente.
  - `telefono`: Teléfono del cliente.
  - `email`: Email del cliente.

### Demás clases de model
Están las demás clases del modelo necesarias para la gestión del proyecto. Al contrario que en el cliente de tipo Java, Android
o equivalente, no se necesita una clase Taller "agregadora" de los demás datos. Esto es una decisión de diseño en el backend.

## Configuración del Proyecto

### `pom.xml`

El archivo `pom.xml` contiene la configuración de Maven para el proyecto, incluyendo las dependencias necesarias.

```xml
<dependencies>
    <!-- Dependencias necesarias -->
    <dependency>
        <groupId>commons-fileupload</groupId>
        <artifactId>commons-fileupload</artifactId>
        <version>1.4</version>
    </dependency>
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.8.6</version>
    </dependency>
</dependencies>

web.xml

El archivo web.xml contiene la configuración del servlet.

<servlet>
    <servlet-name>TallerServlet</servlet-name>
    <servlet-class>com.politecnicomalaga.tallerservlet.TallerServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>TallerServlet</servlet-name>
    <url-pattern>/clientes</url-pattern>
</servlet-mapping>

## Despliegue

Para desplegar la aplicación en un servidor Tomcat 9, sigue estos pasos:

    - Compila el proyecto con Maven: mvn clean package.
    - Copia el archivo war generado en el directorio target a la carpeta webapps de Tomcat. Siguiendo la configuración de nuestros
    servicios con docker compose, dentro de la carpeta web.
    - Inicia el servidor Tomcat. En la carpeta donde está el fichero docker-compose.yml, ejecuta docker-compose up -d

## Uso

La aplicación permite gestionar clientes a través de un formulario web. Puedes añadir nuevos clientes y listar los clientes existentes.
Si ejecutas el docker-compose en un ordenador con ip VVV.XXX.YYY.ZZZ, ve a la URL con tu navegador favorito: VVV.XXX.YYY.ZZZ:8080/TallerServlet/
(El equipo cliente tiene que estar en la misma red que el equipo servidor, o si es el mismo, sustituir la IP por localhost)
 
Licencia

Este proyecto es completamente OPEN SOURCE libre. Copia, mejora, cambia aquello que necesites con libertad, sin necesidad de atribución.
