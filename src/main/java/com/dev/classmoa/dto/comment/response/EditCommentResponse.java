package com.dev.classmoa.dto.comment.response;

import com.dev.classmoa.domain.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditCommentResponse {

    private Boolean isModified = false;
}
