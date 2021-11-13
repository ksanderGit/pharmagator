package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.dataproviders.dto.theIndependentPharmacy.PriceDto;
import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PriceController {
    private final PriceService priceService;

    @GetMapping
    public List<Price> getAll() {
        return this.priceService.findAll();
    }

    @GetMapping("/pharmacyId/{pharmacyId}/medicineId/{medicineId}")
    public ResponseEntity<Price> getById(@PathVariable Long pharmacyId, @PathVariable Long medicineId) {

        return this.priceService.findById(pharmacyId, medicineId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Price create(@Valid @RequestBody PriceDto priceDto) {
        return priceService.save(priceDto);
    }

    @PutMapping("/pharmacyId/{pharmacyId}/medicineId/{medicineId}")
    public ResponseEntity<Price> update(@Valid @RequestBody PriceDto priceDto,
                                        @PathVariable Long pharmacyId,
                                        @PathVariable Long medicineId) {

        return priceService.update(pharmacyId, medicineId, priceDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/pharmacyId/{pharmacyId}/medicineId/{medicineId}")
    public ResponseEntity<?> delete(@PathVariable Long pharmacyId, @PathVariable Long medicineId) {

        priceService.deleteById(pharmacyId, medicineId);

        return ResponseEntity.noContent().build();

    }

}
