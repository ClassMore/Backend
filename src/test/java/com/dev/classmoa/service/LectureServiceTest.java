package com.dev.classmoa.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.dev.classmoa.domain.entity.Lecture;

class LectureServiceTest {

	@Test
	@DisplayName("강의 리스트를 가져온다.")
	void getLectureList() {
		// given
		Lecture lecture = new Lecture("KCS123", "임창준", "카카오", 100000, 90000, "10%", "http://www.naver.com",
			"https://imgnews.pstatic.net/image/030/2023/09/28/0003140524_001_20230928060601172.jpeg?type=w647");
		Long id = 1L;
		
		// when

		// then
	}

	@Test
	@DisplayName("강의 상세정보를 가져온다.")
	void getLectureDetail() {
	}
}