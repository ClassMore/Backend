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

    private Long id;
    private String content;
    private Long memberId;
    private List<FindCommentResponse> comments;
    private String nickname;
    @Builder.Default
    private Boolean isModified = false;

    public FindOpinionResponse(Opinion opinion) {
        this.id = opinion.getId();
        this.content = opinion.getContent();
        this.memberId = opinion.getMember().getId();
        this.nickname = opinion.getMember().getNickname();
        this.comments = opinion.getComments().stream()
                .filter(comment -> !comment.getIsDeleted())
                .map(FindCommentResponse::new).toList();
        this.isModified = opinion.getIsModified();
    }

}
