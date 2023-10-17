package com.dev.classmoa.dto.comment.response;

import com.dev.classmoa.domain.entity.Comment;
import com.dev.classmoa.domain.entity.Opinion;
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
public class FindCommentResponse {

    @Positive
    private Long id;

    @Size(max = 100)
    @NotNull
    private String content;

    private Opinion opinion;

    @NotNull
    private Boolean isModified;

    public FindCommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.opinion = comment.getOpinion();
        this.isModified = comment.getIsModified();
    }

}
