package com.dev.classmoa.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.entity.ViewCount;
import com.dev.classmoa.domain.repository.ViewCountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ViewCountService {
	private final ViewCountRepository viewCountRepository;

	@Transactional
	public void viewCountUp(Lecture lecture){
		viewCountRepository.findByLectureLectureIdAndLectureDate(lecture.getLectureId(), LocalDate.now())
			.orElseGet(() -> viewCountRepository.save(
				ViewCount.builder()
					.viewCount(0)
					.lecture(lecture)
					.build()
			)).viewCountUp();
	}
}
