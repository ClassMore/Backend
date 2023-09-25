package com.dev.classmoa.dto.comment.response;

import com.dev.classmoa.domain.entity.Comment;
import com.dev.classmoa.domain.entity.Opinion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindComment {

    private Long id;
    private String content;
    private Opinion opinion;

    public FindComment(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.opinion = comment.getOpinion();
    }

}
