package com.dev.classmoa.dto.opinion.response;

import java.util.List;

import com.dev.classmoa.domain.entity.Comment;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.entity.Opinion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindOpinionResponse {

    private Long id;
    private String content;
    private Member member;
    private List<Comment> comments;
    private Boolean isModified;

    public FindOpinionResponse(Opinion opinion) {
        this.id = opinion.getId();
        this.content = opinion.getContent();
        this.member = opinion.getMember();
        this.comments = opinion.getComments();
        this.isModified = opinion.getIsModified();
    }

}
