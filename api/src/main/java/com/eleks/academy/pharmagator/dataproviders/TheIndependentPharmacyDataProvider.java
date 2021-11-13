package com.eleks.academy.pharmagator.dataproviders;

import com.eleks.academy.pharmagator.dataproviders.theIndependentPharmacyMappingObject.CategoryMapping.Category;
import com.eleks.academy.pharmagator.dataproviders.theIndependentPharmacyMappingObject.CategoryMapping.NodeCondition;
import com.eleks.academy.pharmagator.dataproviders.theIndependentPharmacyMappingObject.CategoryMapping.ResultCategory;
import com.eleks.academy.pharmagator.dataproviders.theIndependentPharmacyMappingObject.MedicineMapping.MedicineData;
import com.eleks.academy.pharmagator.dataproviders.theIndependentPharmacyMappingObject.MedicineMapping.RequstResultMedicineCategory;
import com.eleks.academy.pharmagator.dataproviders.theIndependentPharmacyMappingObject.MedicineMapping.TreatmentNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.math.BigDecimal;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Qualifier("theIndependentPharmacyDataProvider")
public class TheIndependentPharmacyDataProvider implements DataProvider {

	private final WebClient theIndependentPharmacyWebClient;

	@Value("${pharmagator.data-providers.apteka-theindependentpharmacy.category-fetch-url}")
	private String categoriesFetch;

	@Value("${pharmagator.data-providers.apteka-theindependentpharmacy.category-path}")
	private String medicineFetch;

	@Override
	public Stream<MedicineData> loadData() {
		Stream<MedicineData> medicineDtoResult = fetchCategories().map(Category::getSlug).flatMap(this::getMedicine);
		return medicineDtoResult;
	}

	private Stream<Category> fetchCategories() {
		ResultCategory resultCategory =  theIndependentPharmacyWebClient
				.get()
				.uri(categoriesFetch)
				.retrieve()
				.bodyToMono(ResultCategory.class)
				.block();
		return resultCategory
				.getResult()
				.getData()
				.getAllCondition()
				.getNodes()
				.stream()
				.map(NodeCondition::getEntry);
	}

	private Stream<MedicineData> getMedicine(String category) {
		RequstResultMedicineCategory resultMedicine = theIndependentPharmacyWebClient
				.get()
				.uri(medicineFetch + "/" + category + "/page-data.json")
				.retrieve()
				.bodyToMono(RequstResultMedicineCategory.class)
				.block();
		return resultMedicine
				.getResult()
				.getData()
				.getAllTreatment()
				.getNodes()
				.stream()
				.map(this::buildMedicineDto);
	}

	private MedicineData buildMedicineDto(TreatmentNode treatmentNode) {
		BigDecimal price = BigDecimal.valueOf(Double.parseDouble(treatmentNode.getApiData().getPriceFrom()) / 100);
		treatmentNode.getEntry().setPrice(price);
		MedicineData medicineData = treatmentNode.getEntry();
		return medicineData;
	}

}
