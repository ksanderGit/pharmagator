package com.eleks.academy.pharmagator.dataproviders;

import com.eleks.academy.pharmagator.dataproviders.theIndependentPharmacyMappingObject.MedicineMapping.MedicineData;

import java.util.stream.Stream;

public interface DataProvider {
    Stream<MedicineData> loadData();
}
