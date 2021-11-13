package com.eleks.academy.pharmagator.service;

import com.eleks.academy.pharmagator.dataproviders.dto.theIndependentPharmacy.PharmacyDto;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.repositories.PharmacyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PharmacyServiceImp implements PharmacyService {

    private PharmacyRepository pharmacyRepository;

    @Override
    public List<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }

    public Optional<Pharmacy> findById(Long id) {
        return pharmacyRepository.findById(id);
    }

    @Override
    public Pharmacy save(PharmacyDto pharmacyDto) {
        Pharmacy independentPharmacy = Pharmacy.builder().name(pharmacyDto.getName()).medicineLinkTemplate(pharmacyDto.getMedicineLinkTemplate()).build();
        pharmacyRepository.save(independentPharmacy);
        return independentPharmacy;
    }

    @Override
    public Optional<Pharmacy> update(Long id, PharmacyDto pharmacyDto) {
        Pharmacy independentPharmacy = pharmacyRepository.findById(id).get();
        if (independentPharmacy.equals(null)) {
            throw new RuntimeException();
        }else {
            independentPharmacy.setName(pharmacyDto.getName());
            independentPharmacy.setMedicineLinkTemplate(pharmacyDto.getMedicineLinkTemplate());
            pharmacyRepository.save(independentPharmacy);
        }
        return Optional.of(independentPharmacy);
    }

    @Override
    public void deleteById(Long id) {
        pharmacyRepository.deleteById(id);
    }

}
