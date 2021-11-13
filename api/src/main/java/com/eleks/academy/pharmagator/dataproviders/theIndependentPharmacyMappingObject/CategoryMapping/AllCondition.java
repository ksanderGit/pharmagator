package com.eleks.academy.pharmagator.dataproviders.theIndependentPharmacyMappingObject.CategoryMapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllCondition {

	private List<NodeCondition> nodes;

}
