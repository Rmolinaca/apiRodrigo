README
API CREACION DE USUARIO

1.- importar proyecto Api-rest.
2.- Revisar el application.properties donde se puede configurar el regex para la contraseña.
3.- Realizar un maven update.
4.- Agregar libreria Junit5 para las pruebas automatizadas.
5.- Ejecutar la aplicación desde la clase ApiRestApplication.
6.- Una vez levantada la aplicación ejecutar el api (http://localhost:8080/api/user) pasando un json como el formato solicitado en el body.

{
"name": "Juan Rodriguez",
"email": "juan@rodriguez.org",
"password": "hunter2",
  "phones": [
    {
    "number": "1234567",
    "citycode": "1",
    "contrycode": "57"
    }
  ]
}

7.- Revisar la respuesta.

El diagrama se encuentra en la carpeta Documentacion.
