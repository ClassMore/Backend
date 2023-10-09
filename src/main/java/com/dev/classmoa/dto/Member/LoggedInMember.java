package com.dev.classmoa.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoggedInMember {
	private Long memberId;
	private String memberName;
}
