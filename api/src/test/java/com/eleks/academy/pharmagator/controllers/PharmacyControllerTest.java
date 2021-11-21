package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.dataproviders.dto.theIndependentPharmacy.PharmacyDto;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.service.PharmacyServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = PharmacyController.class)
@Import({PharmacyController.class, ModelMapper.class})
@ActiveProfiles("test")
class PharmacyControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @MockBean
    private PharmacyServiceImp pharmacyService;

    @Autowired
    private MockMvc mockMvc;

    private final String URI = "/pharmacies";
    private List<Pharmacy> pharmacies;

    private Pharmacy pharmacy;

    @BeforeEach
    void setUp() {
        pharmacy = new Pharmacy();
        pharmacy.setId(1L);
        pharmacy.setName("theindependentpharmacy.co.uk");
        pharmacy.setMedicineLinkTemplate("https://www.theindependentpharmacy.co.uk");

        pharmacies = Arrays.asList(pharmacy);
    }

    @Test
    void getAll() throws Exception {
        when(pharmacyService.findAll()).thenReturn(pharmacies);

        mockMvc.perform(get(URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", equalTo(pharmacy.getName())));
        verify(pharmacyService, times(1)).findAll();

    }

    @Test
    void getPharmacyById() throws Exception {
        when(pharmacyService.findById(pharmacy.getId())).thenReturn(Optional.ofNullable(pharmacies.get(0)));

        mockMvc.perform(get(URI + "/" + pharmacy.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(pharmacy.getName())));
    }

    @Test
    void addPharmacy() throws Exception {
        when(pharmacyService.save(any(PharmacyDto.class))).thenReturn(pharmacy);

        mockMvc.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(modelMapper.map(pharmacy, PharmacyDto.class))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(pharmacy.getName()));
    }


    @Test
    void update() throws Exception {
        when(pharmacyService.update(anyLong(), any(PharmacyDto.class)))
                .thenReturn(Optional.ofNullable(pharmacy));

        mockMvc.perform(put(URI + "/" + pharmacy.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modelMapper.map(pharmacy, PharmacyDto.class))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(pharmacy.getName()))
                .andDo(MockMvcResultHandlers.print());

        verify(pharmacyService, times(1)).update(anyLong(), any(PharmacyDto.class));
    }

    @Test
    void deletePharmacy() throws Exception {
        doNothing().when(pharmacyService).deleteById(pharmacy.getId());

        mockMvc.perform(delete(URI + "/" + pharmacy.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        verify(pharmacyService, times(1)).deleteById(anyLong());
    }
}