package com.dev.classmoa.service;

import com.dev.classmoa.domain.entity.InterestLecture;
import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.repository.InterestLectureRepository;
import com.dev.classmoa.dto.interest.response.FindInterestLecturesResponse;
import com.dev.classmoa.dto.interest.response.FindInterestResponse;
import com.dev.classmoa.exception.ClassmoaException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        if (interestLectureRepository.findInterestLectureByMemberIdAndLecture_LectureId(member.getId(), lectureId).isPresent())
            throw new ClassmoaException("좋아요 신청이 완료된 강의입니다.", HttpStatus.FORBIDDEN);
        try {
            interestLectureRepository.save(
                    InterestLecture.builder()
                            .member(member)
                            .lecture(lecture)
                            .build()
            );
        } catch (ClassmoaException ex) {
            throw new ClassmoaException("좋아요 등록 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    //TODO:  예외 처리 [규민]
    public void cancelInterest(Long interestId, Member member) {
        InterestLecture interest = interestLectureRepository.findById(interestId)
                .orElseThrow(() -> new ClassmoaException("좋아요가 취소된 강의입니다.", HttpStatus.FORBIDDEN));

        //TODO: 로직이 변결될 수 있음 [규민, 지훈]
        if (interest.getMember().equals(member)) {
            interestLectureRepository.deleteById(interest.getId());
        }
    }
}
