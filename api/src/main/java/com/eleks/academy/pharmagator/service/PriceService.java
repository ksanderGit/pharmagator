package com.eleks.academy.pharmagator.service;

import com.eleks.academy.pharmagator.dataproviders.dto.theIndependentPharmacy.PriceDto;
import com.eleks.academy.pharmagator.entities.Price;

import java.util.List;
import java.util.Optional;

public interface PriceService {
    List<Price> findAll();

    Optional<Price> findById(Long pharmacyId, Long medicineId);

    Price save(PriceDto priceDto);

    Optional<Price> update(Long pharmacyId, Long medicineId, PriceDto priceDto);

    void deleteById(Long pharmacyId, Long medicineId);
}
