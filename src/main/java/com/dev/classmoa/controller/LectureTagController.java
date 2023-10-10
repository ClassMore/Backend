package com.dev.classmoa.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dev.classmoa.dto.Lecture.response.FindLectureListResponse;
import com.dev.classmoa.service.LectureTagService;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
public class LectureTagController {
	private final LectureTagService lectureTagService;

	@GetMapping("/tag/{id}")
	public ResponseEntity<List<FindLectureListResponse>> getLecturesByTag(@PathVariable("id") @Positive Long tagId) {
		return ResponseEntity.ok(lectureTagService.getLectureListByTag(tagId));
	}
	//TODO: PathVariable

}
