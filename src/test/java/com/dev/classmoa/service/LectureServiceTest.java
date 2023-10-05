package com.dev.classmoa.service;


import com.dev.classmoa.domain.entity.InterestLecture;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.repository.LectureRepository;
import com.dev.classmoa.dto.Lecture.response.FindLectureListResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

	@Autowired
	LectureRepository lectureRepository;

	@AfterEach
	public void init(){

	}

	@BeforeEach
	void CreateTestData() {
		Lecture lecture1 = Lecture.builder()
				.companyName("카클스")
				.imageLink("어딘가 있음")
				.lectureId("카클스1")
				.instructor("임창준")
				.ordinaryPrice(100000)
				.salePercent("30%")
				.salePrice(70000)
				.siteLink("www.ybm.com")
				.title("오사카 여행 가이드")
				.build();

		Lecture lecture2 = Lecture.builder()
				.lectureId("카클스2")
				.companyName("카클스")
				.imageLink("어딘가 있음")
				.instructor("임창준")
				.ordinaryPrice(100000)
				.salePercent("30%")
				.salePrice(70000)
				.siteLink("www.ybm.com")
				.title("오사카 여행 가이드")
				.build();

		Lecture lecture3 = Lecture.builder()
				.lectureId("카클스3")
				.companyName("카클스")
				.imageLink("임창준 아이폰")
				.instructor("임창준")
				.ordinaryPrice(100000)
				.salePercent("30%")
				.salePrice(70000)
				.siteLink("www.창준교토.gg")
				.title("교토 여행 가이드")
				.build();

		lectureRepository.save(lecture1);
		lectureRepository.save(lecture2);
		lectureRepository.save(lecture3);
	}

	@Test
	@DisplayName("페이지 정보를 받아서 강의 리스트를 가져온다.")
	void getLectureList() {
		// given
		Pageable pageable = PageRequest.of(0, 10);
		
		// when
		List<FindLectureListResponse> lectures = lectureService.getLectureList(pageable);

		// then
		assertThat(lectures.size()).isEqualTo(3);
	}

	@Test
	@DisplayName("lectureId를 받아서 강의 상세 정보를 가져온다.")
	void getLectureDetail() {
		// given
		String lectureId = "카클스3";

		// when
		Lecture lectures = lectureService.getLectureDetail(lectureId);

		// then
		assertThat(lectures.getLectureId()).isEqualTo(lectureId);
	}
}