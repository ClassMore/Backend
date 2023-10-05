package com.dev.classmoa.service;


import com.dev.classmoa.dto.Lecture.response.FindLectureListResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.dev.classmoa.domain.entity.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

@SpringBootTest
@Transactional
class LectureServiceTest {

	@Autowired
	LectureService lectureService;

	@Test
	@DisplayName("페이지 정보를 받아서 강의 리스트를 가져온다.")
	void getLectureList() {
		// given
		Pageable pageable = PageRequest.of(0, 10);
		
		// when
		List<FindLectureListResponse> lectures = lectureService.getLectureList(pageable);

		// then
		assertThat(lectures.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("lectureId를 받아서 강의 상세정보를 가져온다.")
	void getLectureDetail() {
		// given
		String lectureId = "artstudyjwLee508";

		// when
		Lecture lectures = lectureService.getLectureDetail(lectureId);

		// then
		assertThat(lectures.getLectureId()).isEqualTo(lectureId);
	}
}