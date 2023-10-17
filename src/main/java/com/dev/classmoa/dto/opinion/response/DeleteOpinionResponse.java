package com.dev.classmoa.dto.opinion.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteOpinionResponse {

	@NotNull
	private Boolean isDeleted = false;
}
