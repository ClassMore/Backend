package com.dev.classmoa.dto.comment.response;

import com.dev.classmoa.domain.entity.Comment;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditCommentResponse {

    @NotNull
    private Boolean isModified = false;
}
