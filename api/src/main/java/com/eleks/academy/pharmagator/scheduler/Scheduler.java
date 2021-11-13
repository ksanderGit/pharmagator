package com.eleks.academy.pharmagator.scheduler;

import com.eleks.academy.pharmagator.dataproviders.DataProvider;
import com.eleks.academy.pharmagator.dataproviders.theIndependentPharmacyMappingObject.MedicineMapping.MedicineData;
import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.repositories.MedicineRepository;
import com.eleks.academy.pharmagator.repositories.PharmacyRepository;
import com.eleks.academy.pharmagator.repositories.PriceRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Component
public class Scheduler {

    private final MedicineRepository medicineRepository;

    private final PriceRepository priceRepository;

    private final PharmacyRepository pharmacyRepository;

    private final List<DataProvider> dataProviderList;

    @Value("${pharmagator.data-providers.apteka-theindependentpharmacy.url}")
    private String pharmacyName;

    private Pharmacy pharmacy;

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void schedule() {
        log.info("Scheduler started at {}", Instant.now());
        dataProviderList.stream().flatMap(DataProvider::loadData).forEach(this::storeToDatabase);
        log.info("successfully");
    }

    private void storeToDatabase(MedicineData dto) {
        if (pharmacy == null) {
            this.storeToDatabesePharmacy();
        }
        Medicine medicine = new Medicine();
        medicine.setTitle(dto.getTitle());
        medicine.setId(Long.parseLong(dto.getId()));
        medicineRepository.save(medicine);

        Price price = new Price();
        price.setMedicineId(medicine.getId());
        price.setPrice(dto.getPrice());
        price.setPharmacyId(pharmacy.getId());
        price.setUpdatedAt(Instant.now());
        priceRepository.save(price);
    }

    private void storeToDatabesePharmacy() {
        pharmacy = new Pharmacy();
        pharmacy.setName(pharmacyName.substring(12));
        pharmacy.setMedicineLinkTemplate(pharmacyName);
        pharmacyRepository.save(pharmacy);
    }

}
