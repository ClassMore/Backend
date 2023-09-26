package com.dev.classmoa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.classmoa.domain.entity.Alarm;
import com.dev.classmoa.domain.entity.InterestLecture;
import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.entity.Opinion;
import com.dev.classmoa.domain.repository.InterestLectureRepository;
import com.dev.classmoa.dto.Lecture.response.FindAlarmLectures;
import com.dev.classmoa.dto.Lecture.response.FindInterestLectures;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterestService {
	private final InterestLectureRepository interestLectureRepository;
	private final LectureService lectureService;

	public List<FindInterestLectures> getLectureListByMember(Long memberId){
		List<InterestLecture> interests = interestLectureRepository.findInterestLecturesByMemberId(memberId);

		return interests.stream()
			.map(InterestLecture::getLecture)
			.map(FindInterestLectures::new)
			.toList();
	}


	public Long create(String lectureId, Member member){
		Lecture lecture = lectureService.getLectureDetail(lectureId);
		return interestLectureRepository.save(
			InterestLecture.builder()
				.member(member)
				.lecture(lecture)
				.build()
		).getId();
	}

	public void delete(InterestLecture interestLecture, Member member) {
		InterestLecture interest = interestLectureRepository.findById(interestLecture.getId())
			.orElseThrow(() -> new IllegalArgumentException("not found"));
		if(interest.getMember().equals(member)) {
			interestLectureRepository.deleteById(interestLecture.getId());
		}
	}
}
