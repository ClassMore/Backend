package com.dev.classmoa.dto.alarm.request;

import com.dev.classmoa.domain.entity.Alarm;
import com.dev.classmoa.domain.entity.Comment;
import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.entity.Opinion;

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
public class FindAlarm {

	private String lectureId;

	public Alarm toEntity() {
		return Alarm.finder()
			.lecture(Lecture.finder().lectureId(lectureId).find())
			.find();
	}
}
