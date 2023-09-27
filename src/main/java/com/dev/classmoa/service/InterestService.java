package com.dev.classmoa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.classmoa.domain.entity.InterestLecture;
import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.repository.InterestLectureRepository;
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

	public Boolean getIsInterested(String lectureId, Member member){
		return interestLectureRepository.existsInterestLectureByMemberIdAndLectureId(
			member.getId(), lectureId);
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

	//TODO:  예외 처리 [규민]
	public void cancel(Long interestId, Member member) {
		InterestLecture interest = interestLectureRepository.findById(interestId)
			.orElseThrow(() -> new IllegalArgumentException("not found"));
		
		//TODO: 로직이 변결될 수 있음 [규민, 지훈]
		if(interest.getMember().equals(member)) {
			interestLectureRepository.deleteById(interestId);
		}
	}
}
