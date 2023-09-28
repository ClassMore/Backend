package com.dev.classmoa.service;

import com.dev.classmoa.domain.entity.InterestLecture;
import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.repository.InterestLectureRepository;
import com.dev.classmoa.dto.Lecture.response.FindInterestLecturesResponse;
import com.dev.classmoa.dto.interest.response.CreateInterestResponse;
import com.dev.classmoa.dto.interest.response.FindInterestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestService {
    private final InterestLectureRepository interestLectureRepository;
    private final LectureService lectureService;

    public List<FindInterestLecturesResponse> getLectureListByMember(Long memberId) {
        List<InterestLecture> interests = interestLectureRepository.findInterestLecturesByMemberId(memberId);

        return interests.stream()
                .map(InterestLecture::getLecture)
                .map(FindInterestLecturesResponse::new)
                .toList();
    }

    public FindInterestResponse getIsInterested(String lectureId, Member member) {
        Boolean isInterested = interestLectureRepository.findInterestLectureByMemberIdAndLecture_LectureId(
                member.getId(), lectureId).isPresent();
        return new FindInterestResponse(isInterested);
    }

    public CreateInterestResponse create(String lectureId, Member member) {
        Lecture lecture = lectureService.getLectureDetail(lectureId);
        InterestLecture interestLecture = interestLectureRepository.save(
                InterestLecture.builder()
                        .member(member)
                        .lecture(lecture)
                        .build()
        );
        return new CreateInterestResponse(interestLecture.getId());
    }

    //TODO:  예외 처리 [규민]
    public void cancel(Long interestId, Member member) {
        InterestLecture interest = interestLectureRepository.findById(interestId)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        //TODO: 로직이 변결될 수 있음 [규민, 지훈]
        if (interest.getMember().equals(member)) {
            interestLectureRepository.deleteById(interest.getId());
        }
    }
}
