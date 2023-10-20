package com.dev.classmoa.dto.comment.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCommentResponse {

	@Builder.Default
	private Boolean isDeleted = false;
}
