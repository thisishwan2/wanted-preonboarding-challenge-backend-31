package com.example.cqrs_challenge.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "sellers")
public class Seller extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(length = 255)
    private String logoUrl;

    @Column(precision = 3, scale = 2)
    private BigDecimal rating;

    @Column(length = 100)
    private String contactEmail;

    @Column(length = 20)
    private String contactPhone;
}
