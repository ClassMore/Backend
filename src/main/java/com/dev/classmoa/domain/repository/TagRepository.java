package com.dev.classmoa.domain.repository;

import com.dev.classmoa.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
