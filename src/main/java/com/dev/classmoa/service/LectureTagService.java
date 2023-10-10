package com.dev.classmoa.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.classmoa.domain.entity.LectureTag;
import com.dev.classmoa.domain.repository.LectureTagRepository;
import com.dev.classmoa.dto.Lecture.response.FindLectureListResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectureTagService {

	private final LectureTagRepository lectureTagRepository;

	@Transactional
	public List<FindLectureListResponse> getLectureListByTag(Long tagId) {
		List<LectureTag> lectureTags = lectureTagRepository.findLectureTagsByTagId(tagId);

		return lectureTags.stream()
			.filter(lectureTag -> lectureTag.getLecture() != null)
			.map(LectureTag::getLecture)
			.map(FindLectureListResponse::new)
			.toList();
	}

}
