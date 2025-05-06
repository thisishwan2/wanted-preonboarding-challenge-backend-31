package com.example.cqrs_challenge.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "reviews")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Integer rating;

    @Column(length = 255)
    private String title;

    @Column(columnDefinition = "text")
    private String content;

    @Column(nullable = false)
    private Boolean verifiedPurchase = false;

    @Column(nullable = false)
    private Integer helpfulVotes = 0;
}

