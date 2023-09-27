package com.dev.classmoa.dto.comment.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCommentResponse {
	Boolean isDeleted;
}
