package com.dev.classmoa.domain.repository;

import com.dev.classmoa.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
