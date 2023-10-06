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
    public Void createInterest(String lectureId, Member member) {
        Lecture lecture = lectureService.getLectureDetail(lectureId);
        InterestLecture interestLecture = interestLectureRepository.findInterestLectureByMemberIdAndLecture_LectureId(member.getId(), lectureId)
                .orElseGet(() -> interestLectureRepository.save(InterestLecture.builder()
                                .member(member)
                                .lecture(lecture)
                                .build()));

        if (interestLecture.isCanceled()) {
            interestLecture.updateIsCanceled(false);
            return null;
        }
        interestLecture.updateIsCanceled(true);
        return null;
    }

}
