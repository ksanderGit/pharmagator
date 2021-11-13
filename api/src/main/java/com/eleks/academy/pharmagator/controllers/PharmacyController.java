package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.dataproviders.dto.theIndependentPharmacy.PharmacyDto;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.service.PharmacyServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pharmacies")
public class PharmacyController {

    private final PharmacyServiceImp pharmacyService;

    @GetMapping
    public List<Pharmacy> getAll() {
        return pharmacyService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pharmacy> getPharmacyById(@PathVariable Long id) {

        return pharmacyService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pharmacy> addPharmacy(@RequestBody PharmacyDto pharmacy) {

        return ResponseEntity.ok(pharmacyService.save(pharmacy));

    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Pharmacy> update(@PathVariable("id") Long id, @Valid @RequestBody PharmacyDto pharmacy) {

        Optional<Pharmacy> pharmacyId = pharmacyService.update(id, pharmacy);

        return pharmacyId.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());

    }

    @DeleteMapping(path = "{pharmacyId}")
    public void deletePharmacy(@PathVariable("pharmacyId") Long id) {
        pharmacyService.deleteById(id);
    }
}
