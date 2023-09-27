package com.dev.classmoa.dto.interest.request;

import com.dev.classmoa.domain.entity.Alarm;
import com.dev.classmoa.domain.entity.InterestLecture;
import com.dev.classmoa.domain.entity.Lecture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindInterest {
	private String lectureId;

	public InterestLecture toEntity() {
		return InterestLecture.finder()
			.lecture(Lecture.finder().lectureId(lectureId).find())
			.find();
	}
}
