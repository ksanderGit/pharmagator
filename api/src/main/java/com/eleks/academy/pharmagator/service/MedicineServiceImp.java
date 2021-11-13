package com.eleks.academy.pharmagator.service;

import com.eleks.academy.pharmagator.dataproviders.dto.theIndependentPharmacy.MedicineDto;
import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.repositories.MedicineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MedicineServiceImp implements MedicineService {

    private MedicineRepository medicineRepository;

    @Override
    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }

    @Override
    public Optional<Medicine> findById(Long id) {
        return medicineRepository.findById(id);
    }

    @Override
    public Medicine save(MedicineDto medicineDto) {
        Medicine medicine = Medicine.builder().title(medicineDto.getTitle()).id(medicineRepository.count()).build();
        medicineRepository.save(medicine);
        return medicine;
    }

    @Override
    public Optional<Medicine> update(Long id, MedicineDto medicineDto) {
        Optional<Medicine> medicine = medicineRepository.findById(id);
        if (medicine.isPresent()) {
            medicine.get().setTitle(medicineDto.getTitle());
            medicineRepository.save(medicine.get());
        }else throw new UnsupportedOperationException();
        return medicine;
    }

    @Override
    public void delete(Long id) {
        medicineRepository.deleteById(id);
    }
}
