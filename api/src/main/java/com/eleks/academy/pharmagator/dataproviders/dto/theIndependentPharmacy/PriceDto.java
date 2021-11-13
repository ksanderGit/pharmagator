package com.eleks.academy.pharmagator.dataproviders.dto.theIndependentPharmacy;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class PriceDto {

    @Min(value = 0)
    private BigDecimal price;

    private String externalId;
}
