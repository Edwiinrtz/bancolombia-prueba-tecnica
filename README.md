# Prueba Técnica - Ingeniero de Software (Java + Spring Boot + Arquitectura Hexagonal)
-------------
## Requisitos

- Java 17 JDK
- Mysql (credenciales y nombre de base de datos especificados en aplication.properties)
- Docker (pruebas de integracion)

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


# REPORTE DE COBERTURA



<!DOCTYPE html>
<html id="htmlId">
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"> 
  <title>Coverage Report > Summary</title>
  <style type="text/css">
    @import "./css/coverage.css";
    @import "./css/idea.min.css";
  </style>
</head>

<body>
<div class="content">
<div class="breadCrumbs">
Current scope:     all classes
</div>

<h1>Overall Coverage Summary </h1>
<table class="coverageStats">
  <tr>
    <th class="name">Package</th>
<th class="coverageStat 
">
  Class, %
</th>
<th class="coverageStat 
">
  Method, %
</th>
<th class="coverageStat 
">
  Line, %
</th>
  </tr>
  <tr>
    <td class="name">all classes</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (8/8)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (16/16)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (66/66)
  </span>
</td>
  </tr>
</table>

<br/>
<h2>Coverage Breakdown</h2>

<table class="coverageStats">
<tr>
  <th class="name  sortedAsc
">
<a href="index_SORT_BY_NAME_DESC.html">Package</a>  </th>
<th class="coverageStat 
">
  <a href="index_SORT_BY_CLASS.html">Class, %</a>
</th>
<th class="coverageStat 
">
  <a href="index_SORT_BY_METHOD.html">Method, %</a>
</th>
<th class="coverageStat 
">
  <a href="index_SORT_BY_LINE.html">Line, %</a>
</th>
</tr>
  <tr>
    <td class="name"><a href="ns-1/index.html">com.bancolombia.puebatecnica.application.services</a></td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (2/2)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (4/4)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (33/33)
  </span>
</td>
  </tr>
  <tr>
    <td class="name"><a href="ns-2/index.html">com.bancolombia.puebatecnica.application.utils</a></td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (1/1)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (2/2)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (3/3)
  </span>
</td>
  </tr>
  <tr>
    <td class="name"><a href="ns-3/index.html">com.bancolombia.puebatecnica.domain.models</a></td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (1/1)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (2/2)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (2/2)
  </span>
</td>
  </tr>
  <tr>
    <td class="name"><a href="ns-4/index.html">com.bancolombia.puebatecnica.infrastructure.in.controllers</a></td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (2/2)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (4/4)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (20/20)
  </span>
</td>
  </tr>
  <tr>
    <td class="name"><a href="ns-5/index.html">com.bancolombia.puebatecnica.infrastructure.out.adapters</a></td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (2/2)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (4/4)
  </span>
</td>
<td class="coverageStat">
  <span class="percent">
    100%
  </span>
  <span class="absValue">
    (8/8)
  </span>
</td>
  </tr>
</table>
</div>

</body>
</html>
