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

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestService {
    private final InterestLectureRepository interestLectureRepository;
    private final LectureService lectureService;

    public List<FindInterestLecturesResponse> getInterestListByMember(Long memberId) {
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

    //TODO: 포스트맨 쓰는 싸가지들 처리  (좋아요 중복 처리) 반환 dto없애고 객체 없애고 save 예외처리
    public void createInterest(String lectureId, Member member) {
        Lecture lecture = lectureService.getLectureDetail(lectureId);
        try {
            interestLectureRepository.save(
                    InterestLecture.builder()
                            .member(member)
                            .lecture(lecture)
                            .build()
            );
        } catch(Exception e) {
            e.getMessage();
            throw e;
        }
    }

    //TODO:  예외 처리 [규민]
    public void cancelInterest(Long interestId, Member member) {
        InterestLecture interest = interestLectureRepository.findById(interestId)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        //TODO: 로직이 변결될 수 있음 [규민, 지훈]
        try {
            if (interest.getMember().equals(member)) {
                interestLectureRepository.deleteById(interest.getId());
            }
        } catch(Exception e) {
            e.getMessage();
            throw e;
        }
    }
}
