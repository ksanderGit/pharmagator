package com.eleks.academy.pharmagator.repositories;

import com.eleks.academy.pharmagator.entities.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}
