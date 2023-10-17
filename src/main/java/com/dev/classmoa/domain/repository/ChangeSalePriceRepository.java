package com.dev.classmoa.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.classmoa.domain.entity.ChangeSalePrice;

public interface ChangeSalePriceRepository extends JpaRepository<ChangeSalePrice, String> {
}
