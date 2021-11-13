package com.eleks.academy.pharmagator.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "medicines")
public class Medicine {

    @Id
    private Long id;

    private String title;
}
