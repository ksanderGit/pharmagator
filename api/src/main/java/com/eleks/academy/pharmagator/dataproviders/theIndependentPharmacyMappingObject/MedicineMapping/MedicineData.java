package com.eleks.academy.pharmagator.dataproviders.theIndependentPharmacyMappingObject.MedicineMapping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicineData {

	private String id;

	private String title;

	private BigDecimal price;

}
