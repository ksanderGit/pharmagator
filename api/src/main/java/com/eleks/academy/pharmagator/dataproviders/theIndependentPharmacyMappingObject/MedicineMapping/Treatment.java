package com.eleks.academy.pharmagator.dataproviders.theIndependentPharmacyMappingObject.MedicineMapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Treatment {

	private List<TreatmentNode> nodes;

}
