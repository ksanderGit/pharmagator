package com.eleks.academy.pharmagator.dataproviders.dto.theIndependentPharmacy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
public class PharmacyDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String medicineLinkTemplate;
}
