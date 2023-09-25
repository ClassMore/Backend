package com.dev.classmoa.domain.repository;

import com.dev.classmoa.domain.entity.ViewCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewCountRepository extends JpaRepository<ViewCount, String> {

}
