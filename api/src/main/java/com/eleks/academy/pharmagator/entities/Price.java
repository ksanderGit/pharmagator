package com.eleks.academy.pharmagator.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prices")
@IdClass(PriceId.class)
public class Price {

    @Id
    private Long pharmacyId;

    @Id
    private Long medicineId;

    private BigDecimal price;

    @LastModifiedDate
    private Instant updatedAt;
}
