package com.eleks.academy.pharmagator.dataproviders.dto.theIndependentPharmacy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDto {

    @NotEmpty
    private String title;
}
