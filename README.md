# Servicio de Creación de Usuarios

Este servicio facilita la creación de usuarios y ha sido desarrollado como parte de una evaluación de habilidades. Es importante tener en cuenta que el proyecto se encuentra en una fase de desarrollo y no se recomienda su utilización en entornos de producción.

## Documentos
En la carpeta Documentacion se encuentra el diagrama de la solucion.

## Estado del Proyecto
En desarrollo

## Instalación del Proyecto
Siga estos pasos para instalar y ejecutar el proyecto en su máquina local.

### Requisitos Previos
Asegúrese de tener instalados los siguientes elementos en su entorno de desarrollo:
- Java Development Kit (JDK) - versión 8 o superior.
- Maven - herramienta de construcción y gestión de dependencias.

### Pasos de Instalación
1. **Clonar el Repositorio:**

    git clonegit@github.com:Rmolinaca/apiRodrigo.git
  

3. **Configurar la Base de Datos H2 (Opcional):**
    El proyecto puede utilizar H2 como base de datos embebida. Modifique la configuración en `src/main/resources/application.properties` según sus necesidades.


4. **Importar la Aplicación con Eclipse :**


   File > Import > Maven > Existing Maven Projects

   Buscar la ruta de la api, seleccionar el proyeco y presionar Finish.
   
5. **Configurar la Aplicación en Eclipse :**

   Realizar un maven update y agregar la Classpath de spring-tool.
  
6. **Ejecutar la Aplicación**

    Ejecutar la clase ApiRestApplication.

## Uso de la Aplicación
1. **Acceder a la Aplicación:**
    La aplicación estará disponible en http://localhost:8080.

2. **Probar la API:**
    Utilice herramientas como Postman o curl para interactuar con la API.

3. **Crear un Usuario:**
    - Endpoint: `http://localhost:8080/api/user`
    - Método: `POST`
    - Cuerpo de la Solicitud:
        ```json
        {
          "name": "Nombre Apellido",
          "email": "correo@dominio.com",
          "password": "contrasena",
          "phones": [
            {
             "number": 123456789,
             "citycode": 1,
             "countrycode": 57
            } 
          ]
        }
        ```

    - Ejemplo de Solicitud en CURL:    
        ```
        curl --location --request POST 'http://localhost:8080/api/user' \
        --header 'Content-Type: application/json' \
        --data-raw '{
            "name": "Vanesa Cifuentes",
            "email": "vanne@cifuentes.org",
            "password": "vanne123",
            "phones": [
                {
                    "number": "1234567",
                    "citycode": "4",
                    "countrycode": "22"
                },
                {
                    "number": "7654321",
                    "citycode": "22",
                    "countrycode": "572"
                }
            ]
        }'
        ```
    - Respuesta Exitosa, con el Token que se va a usar en el sigueinte end-pont:
        ```json

            {
                "userId": "1",
                "name": "Vanesa Cifuentes",
                "email": "vanne@cifuentes.org",
                "password": "vanne123",
                "phones": [
                    {
                        "number": 1234567,
                        "citycode": 1,
                        "contrycode": 22
                    },
                    {
                        "number": 7654321,
                        "citycode": 22,
                        "contrycode": 527
                    }
                ],
                "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2YW5uZUBjaWZ1ZW50ZXMub3JnIiwibmFtZSI6IlZhbmVzYSBDaWZ1ZW50ZXMifQ.ui5GmxIDzoPHLc1Ghx4qqh33XFDD6odukUVwJFcr8gI",
                "created": "2024-04-10T22:30:24.244+00:00",
                "modified": "2024-04-10T22:30:24.244+00:00",
                "lastLogin": "2024-04-10T22:30:24.244+00:00",
                "isactive": true
            }
        ```


    - Respuesta Fallida, validación de correo único:
        ```json        
        {
            "error": [
                {
                    "mensaje": "El correo ya registrado"
                }
            ]
        }        
        ```

    - Respuesta Fallida, password no cumple con el formato:
        ```json
        {
            "error": [
                {
                    "mensaje": "La clave ingresada no es válida."
                }
            ]
        }
        ```





