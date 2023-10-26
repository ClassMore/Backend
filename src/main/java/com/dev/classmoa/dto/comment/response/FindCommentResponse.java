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

    private Long id;
    private String content;
    private Long opinionId;
    private String nickname;
    private Long memberId;
    private Boolean isModified;

    public FindCommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.nickname = comment.getMember().getNickname();
        this.opinionId = comment.getOpinion().getId();
        this.memberId = comment.getMember().getId();
        this.isModified = comment.getIsModified();
    }
}
