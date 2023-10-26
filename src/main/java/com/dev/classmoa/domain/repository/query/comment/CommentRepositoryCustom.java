package com.dev.classmoa.domain.repository.query.comment;

import com.dev.classmoa.domain.entity.Comment;
import com.dev.classmoa.domain.entity.Opinion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepositoryCustom {
    List<Comment> getComments(Opinion opinion, String lectureId);
}
