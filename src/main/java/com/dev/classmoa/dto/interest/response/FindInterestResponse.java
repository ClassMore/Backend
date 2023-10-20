package com.dev.classmoa.dto.interest.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindInterestResponse {

	@Builder.Default
	private Boolean isInterested = false;
}

