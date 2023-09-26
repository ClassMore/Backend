package com.dev.classmoa.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.classmoa.domain.entity.InterestLecture;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.dto.Lecture.response.FindInterestLectures;
import com.dev.classmoa.dto.opinion.request.CreateOpinion;
import com.dev.classmoa.service.InterestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InterestController {

	private final InterestService interestService;

	// 좋아요 한 강의 목록 조회
	@GetMapping("/interest")
	public ResponseEntity<List<FindInterestLectures>> getInterests(Member member){
		return ResponseEntity.ok(interestService.getLectureListByMember(member.getId()));
	}

	// 좋아요 등록
	@PostMapping("/interest/{lecture_id}")
	public Long createInterest(@PathVariable String lectureId, Member member){
		return interestService.create(lectureId, member);
	}

	// 좋아요 삭제
	@DeleteMapping("/interest/{lecture_id}")
	public void deleteInterest(InterestLecture interestLecture, Member member){
		interestService.delete(interestLecture, member);
	}
}
