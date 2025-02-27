package com.bancolombia.puebatecnica.infrastructure.integration;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bancolombia.puebatecnica.infrastructure.config.MysqlTestContainerConfiguration;
import com.bancolombia.puebatecnica.infrastructure.out.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class AccountControllerIntegrationTest extends MysqlTestContainerConfiguration {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp(){
        accountRepository.deleteAll();
    }

    @Test
    void createAccount() throws Exception {
        String requestBody = """
            {
                "name":"Edwin",
                "surname":"Palacios",
                "identification":"10003999852",
                "identificationType":"cc"
            }
            """;

        mockMvc.perform(MockMvcRequestBuilders.post("/account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string(startsWith("Account created: ")));
    }

    @Test
    void getBalance() throws Exception {
        // Setup: Create account first
        String createResponse = mockMvc.perform(MockMvcRequestBuilders.post("/account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {"name":"Test","surname":"User","identification":"12345","identificationType":"cc"}
                    """))
                .andReturn().getResponse().getContentAsString();

        String accountNumber = createResponse.split(": ")[1];

        // Test balance retrieval
        mockMvc.perform(MockMvcRequestBuilders.get("/account/balance")
                        .param("accountNumber", accountNumber))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Account balance:")));
    }

}