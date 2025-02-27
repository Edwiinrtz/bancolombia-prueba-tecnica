# Prueba Técnica - Ingeniero de Software (Java + Spring Boot + Arquitectura Hexagonal)
-------------

## Descripcion

### Objetivo:
Desarrollar una API REST para la gestión de cuentas bancarias aplicando arquitectura
hexagonal y buenas prácticas de desarrollo en Java y Spring Boot.
### Requisitos Técnicos:

- Usar Java y Spring Boot
- Aplicar arquitectura hexagonal (Separar dominio, aplicación e infraestructura).
- Implementar pruebas unitarias y de integración.
- Usar JPA y H2 para persistencia en memoria.
- Exponer endpoints REST con Spring Web.
- Aplicar principios SOLID y buenas prácticas de código limpio.


---------

## Funcionamiento


### POST /account/create

Crear una nueva cuenta bancaria y debe recibir un objecto JSON con la siguiente informacion.


    {
        "name":"nombre",
        "surname":"apellido",
        "identification":"0000000000",
        "identificationType":"tipo-de-documento"
    }
#### Respuesta satisfactoria:

    "Account created: XXXXXXXXXX"

donde **XXXXXXXXXX** es el numero de cuenta que este usuario tendra asignada.

un usuario puede tener multiples cuentas, pero el numero de cuenta es unico.

#### Respuesta error:

    {
        "code":"account-error",
        "message":"mensaje de error especifico"
    }


#### GET /account/balance?{accountNumber}

Consultar el balance de una cuenta especifica filtrada por el parametro **accountNumber**

#### Respuesta satisfactoria:

    Account balance:XXXX

donde **XXXX** es el total del dinero disponble en la cuenta.

#### Respuesta error:

    {
        "code": "account-error",
        "message": "mensaje de error especifico"
    }

### POST /transaction/new
    {
        "transactionType":0,
        "account":"XXXXXXXXXX",
        "value":XXXX
    }

- *transactionType*: tiene 2 opciones disponibles. **0: Depositos. 1: Retiros.**
- *account*: debe ser una cuenta existente.
- *value*: dene ser un numero mayor a 0

#### Respuesta satisfactoria:

    Transaccion finalizada con exito.

#### Respuesta error:

    {
        "code": "transaction-error",
        "message": "mensaje de error especifico"
    }


### GET /transaction?{account}

Conocer el listado de movimientos de una cuenta en especifico.

#### Respuesta satisfactoria cuenta sin movimientos:

    []

#### Respuesta satisfactoria:

    [
        {
            "id": 1,
            "transactionType": "DEPOSIT",
            "account": "XXXXXXXXXX",
            "amount": 1000
        },
        {
            "id": 2,
            "transactionType": "WITHDRAWAL",
            "account": "XXXXXXXXXX",
            "amount": 1000
        }
    ]


#### Respuesta error:

    {
        "code": "transaction-error",
        "message": "mensaje de error especifico"
    }

### Documentacion swagger.

La applicacion debe estar en ejecucion.

http://localhost:8080/swagger-ui/index.html