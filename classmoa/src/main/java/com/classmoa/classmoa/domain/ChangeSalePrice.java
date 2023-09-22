package com.classmoa.classmoa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class ChangeSalePrice {

    @Id
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "lecture_id", referencedColumnName = "lecture_id")
    private Lecture lecture;

    private int priorSalePrice;
    private int newSalePrice;
}
