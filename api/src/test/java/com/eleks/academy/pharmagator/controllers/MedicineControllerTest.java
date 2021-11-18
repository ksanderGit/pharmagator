package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.config.ModelMapperConfig;
import com.eleks.academy.pharmagator.dataproviders.dto.theIndependentPharmacy.MedicineDto;
import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.service.MedicineServiceImp;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(controllers = MedicineController.class)
@Import({MedicineController.class, ModelMapperConfig.class})
class MedicineControllerTest {

    @MockBean
    private MedicineServiceImp medicineService;


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;

    private final String URI = "/medicines";
    private Medicine medicineFirst;
    private Medicine medicineSecond;
    private List<Medicine> medicineList;


    @BeforeEach
    void setUp() {
        medicineFirst = new Medicine(79189L, "Famotidine 20mg Tablets");
        medicineSecond = new Medicine(79674L, "Gaviscon Double Action Tablets");
        medicineList = List.of(medicineFirst, medicineSecond);
    }

    @Test
    void getAll() throws Exception {
        when(medicineService.findAll()).thenReturn(medicineList);

        mockMvc.perform(MockMvcRequestBuilders.get(URI).contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());

        verify(medicineService, times(1)).findAll();
    }

    @Test
    void getById() throws Exception {
        when(medicineService.findById(medicineFirst.getId())).thenReturn(Optional.ofNullable(medicineFirst));

        mockMvc.perform(get(URI + "/" + medicineFirst.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo(medicineFirst.getTitle())));
    }


    @Test
    void create() throws Exception {
        when(medicineService.save(any(MedicineDto.class))).thenReturn(medicineFirst);

        mockMvc.perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modelMapper.map(medicineFirst, MedicineDto.class))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(medicineFirst.getTitle()))
                .andDo(MockMvcResultHandlers.print());

        verify(medicineService, times(1)).save(any(MedicineDto.class));
    }

    @Test
    void update() throws Exception {
        when(medicineService.update(anyLong(), any(MedicineDto.class)))
                .thenReturn(Optional.ofNullable(medicineFirst));

        mockMvc.perform(put(URI + "/" + medicineFirst.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modelMapper.map(medicineFirst, MedicineDto.class))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(medicineFirst.getTitle()))
                .andDo(MockMvcResultHandlers.print());

        verify(medicineService, times(1)).update(anyLong(), any(MedicineDto.class));
    }

    @Test
    void deleteWithId() throws Exception {
        doNothing().when(medicineService).delete(medicineFirst.getId());

        mockMvc.perform(delete(URI + "/" + medicineFirst.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());

        verify(medicineService, times(1)).delete(anyLong());
    }
}