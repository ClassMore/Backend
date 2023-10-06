package com.dev.classmoa.dto.opinion.response;

import java.util.List;

import com.dev.classmoa.domain.entity.Comment;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.entity.Opinion;
import com.dev.classmoa.dto.comment.response.FindCommentResponse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindOpinionResponse {

    @Positive
    private Long id;

    @Size(max = 100)
    @NotNull
    private String content;

    private Member member;
    private List<FindCommentResponse> comments;

    @NotNull
    private Boolean isModified = false;

    public FindOpinionResponse(Opinion opinion) {
        this.id = opinion.getId();
        this.content = opinion.getContent();
        this.member = opinion.getMember();
        this.comments = opinion.getComments().stream().map(FindCommentResponse::new).toList();
        this.isModified = opinion.getIsModified();
    }

}
