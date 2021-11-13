package com.eleks.academy.pharmagator.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceId implements Serializable {
    private Long pharmacyId;
    private Long medicineId;
}
