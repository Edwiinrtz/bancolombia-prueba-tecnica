package com.bancolombia.puebatecnica.infrastructure.integration;

import com.bancolombia.puebatecnica.infrastructure.config.MysqlTestContainerConfiguration;
import com.bancolombia.puebatecnica.infrastructure.out.repositories.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class TransactionControllerIntegrationTest extends MysqlTestContainerConfiguration {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp(){
        transactionRepository.deleteAll();
    }


    @Test
    void  newTransaction() throws Exception {


        String createResponse = mockMvc.perform(MockMvcRequestBuilders.post("/account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {"name":"Test","surname":"User","identification":"12345","identificationType":"cc"}
                    """))
                .andReturn().getResponse().getContentAsString();

        String accountNumber = createResponse.split(": ")[1];

        String bodyDeposit = String.format("""
                {
                    "transactionType":0,
                    "account":%s,
                    "value":1000
                }
                """, accountNumber);

        String bodyWithdrawal = String.format("""
                {
                    "transactionType":1,
                    "account":%s,
                    "value":1000
                }
                """, accountNumber);


        mockMvc.perform(MockMvcRequestBuilders
                .post("/transaction/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyDeposit))
                .andExpect(status().isOk())
                .andExpect(content().string("Transaccion finalizada con exito."));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/transaction/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyWithdrawal))
                .andExpect(status().isOk())
                .andExpect(content().string("Transaccion finalizada con exito."));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/transaction/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyWithdrawal))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{\"code\":\"transaction-error\",\"message\":\"Fondos insuficientes\"}"));
    }

    @Test
    void getAllByAccount() throws Exception {


        String createResponse = mockMvc.perform(MockMvcRequestBuilders.post("/account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {"name":"Test","surname":"User","identification":"12345","identificationType":"cc"}
                    """))
                .andReturn().getResponse().getContentAsString();

        String accountNumber = createResponse.split(": ")[1];

        String emptyListString = mockMvc.perform(MockMvcRequestBuilders
                .get("/transaction")
                .param("account", accountNumber))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertEquals("[]", emptyListString);

        String bodyDeposit = String.format("""
                {
                    "transactionType":0,
                    "account":%s,
                    "value":1000
                }
                """, accountNumber);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/transaction/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyDeposit))
                .andExpect(status().isOk())
                .andExpect(content().string("Transaccion finalizada con exito."));


        String noEmptyListString = mockMvc.perform(MockMvcRequestBuilders
                        .get("/transaction")
                        .param("account", accountNumber))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertNotEquals("[]", noEmptyListString);
        Assertions.assertEquals(
                String.format("[{\"id\":1,\"transactionType\":\"DEPOSIT\",\"account\":\"%s\",\"amount\":1000}]",accountNumber),
                noEmptyListString);

    }



}
