package com.moura.avengersapi.application.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moura.avengersapi.application.web.request.AvengerReq;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
class AvengerControllerTest {

    private static final String URL = "/api/v1/avengers";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveInteragirComAAPISemNenhumProblema() throws Exception {
        // Cadastra avenger
        AvengerReq avengerDTO = new AvengerReq("spider-man", "Peter Parker", "sobre poderes", "a história...");
        String valueAsString = objectMapper.writeValueAsString(avengerDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(valueAsString))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nick").value(avengerDTO.nick()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.person").value(avengerDTO.person()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(avengerDTO.description()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.history").value(avengerDTO.history()));

        // Recupera detalhes de um avenger
        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/1/detail"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nick").value(avengerDTO.nick()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.person").value(avengerDTO.person()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(avengerDTO.description()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.history").value(avengerDTO.history()));

        // Deleta o avenger cadastrado anteriormente
        mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isAccepted());

        // Confirma se a lista de avengers está vazia
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

}