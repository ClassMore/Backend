package com.example.classmoa.domain.repository;

import com.example.classmoa.domain.entity.ViewCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewCountRepository extends JpaRepository<ViewCount, String> {

}