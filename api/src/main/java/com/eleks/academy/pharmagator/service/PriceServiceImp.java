package com.eleks.academy.pharmagator.service;

import com.eleks.academy.pharmagator.dataproviders.dto.theIndependentPharmacy.PriceDto;
import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.entities.PriceId;
import com.eleks.academy.pharmagator.repositories.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceServiceImp implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public List<Price> findAll() {
        return priceRepository.findAll();
    }

    public Optional<Price> findById(Long pharmacyId, Long medicineId) {
        PriceId priceId = new PriceId(pharmacyId, medicineId);
        return this.priceRepository.findById(priceId);
    }

    @Override
    public Price save(PriceDto priceDto) {
        Price price = Price.builder().medicineId(priceRepository.count()).pharmacyId(priceRepository.count()).price(priceDto.getPrice()).updatedAt(Instant.now()).build();
        return priceRepository.save(price);
    }

    @Override
    public Optional<Price> update(Long pharmacyId, Long medicineId, PriceDto priceDto) {

        PriceId priceId = new PriceId(pharmacyId, medicineId);

        if (priceRepository.findById(priceId).isPresent()) {
            Price price = Price.builder()
                    .price(priceDto.getPrice())
                    .build();
            price.setPharmacyId(pharmacyId);
            price.setMedicineId(medicineId);
            return Optional.of(priceRepository.save(price));
        }else {
            return Optional.empty();
        }

    }

    @Override
    public void deleteById(Long pharmacyId, Long medicineId) {
        PriceId priceId = new PriceId(pharmacyId, medicineId);
        priceRepository.deleteById(priceId);
    }
}
