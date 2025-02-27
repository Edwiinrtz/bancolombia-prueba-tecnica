package com.bancolombia.puebatecnica.infrastructure.config;


import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class MysqlTestContainerConfiguration {

    // Contenedor de MySQL con una imagen específic
    static final MySQLContainer<?> mysql = new MySQLContainer<>(DockerImageName.parse("mysql"))
            .withDatabaseName("accountsdba")
            .withUsername("user")
            .withPassword("password");

    static {
        mysql.start(); // Iniciar el contenedor antes de cualquier prueba
    }

    // Configuración dinámica de propiedades para que Spring Boot use el contenedor
    @DynamicPropertySource
    static void databaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }
}
