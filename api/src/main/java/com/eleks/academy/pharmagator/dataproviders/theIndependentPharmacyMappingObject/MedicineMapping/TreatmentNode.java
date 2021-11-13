package com.eleks.academy.pharmagator.dataproviders.theIndependentPharmacyMappingObject.MedicineMapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentNode {

	private MedicineData entry;

	private PriceMedicine apiData;

}
