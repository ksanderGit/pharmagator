package com.eleks.academy.pharmagator.dataproviders.theIndependentPharmacyMappingObject.MedicineMapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheIndependentPharmacyResponse {

	private List<MedicineData> products;
}
