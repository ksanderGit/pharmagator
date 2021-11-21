package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.repositories.PriceRepository;
import com.eleks.academy.pharmagator.service.PriceServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PriceControllerTest {
    private final String URI = "/prices";

    private final Long MedicineID = 79189L;
    private final Long PharmacyID = 1L;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private PriceRepository pharmacyRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PriceServiceImp priceService;


    @Test
    void getAll() throws Exception {
        mockMvc.perform(get(URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", equalTo(priceService.findAll().size())));
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get(URI
                        + "/pharmacyId/{pharmacyId}/medicineId/{medicineId}", PharmacyID, MedicineID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(31.99));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"price\": \"150.5\", \"externalId\": \"227684931\"}"))
                .andExpect(jsonPath("$.price").value(150.5));
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(put(URI + "/pharmacyId/{pharmacyId}/medicineId/{medicineId}", PharmacyID, MedicineID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"price\": \"150.5\", \"externalId\": \"227684931\"}"))
                .andExpect(jsonPath("$.price").value(150.5));
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(URI + "/pharmacyId/{pharmacyId}/medicineId/{medicineId}", PharmacyID, MedicineID))
                .andExpect(status().isNoContent());
    }
}