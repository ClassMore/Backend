package com.dev.classmoa.dto.opinion.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteOpinionResponse {
	Boolean isDeleted;
}
