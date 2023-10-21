package com.dev.classmoa.service;

import com.dev.classmoa.domain.entity.InterestLecture;
import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.repository.InterestLectureRepository;
import com.dev.classmoa.dto.Member.LoggedInMember;
import com.dev.classmoa.dto.interest.response.FindInterestLecturesResponse;
import com.dev.classmoa.dto.interest.response.FindInterestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InterestService {
    private final InterestLectureRepository interestLectureRepository;
    private final LectureService lectureService;
    private final MemberService memberService;

    public List<FindInterestLecturesResponse> getInterestListByMember(LoggedInMember member) {
        List<InterestLecture> interests = interestLectureRepository
                .findInterestLecturesByMemberMemberNameAndLectureDateAndCanceledIsFalse(member.getMemberName(),
                        LocalDate.now());

        return interests.stream()
                .map(InterestLecture::getLecture)
                .map(FindInterestLecturesResponse::new)
                .toList();
    }

    public FindInterestResponse getIsInterested(String lectureId, LoggedInMember member) {
        InterestLecture interestLecture = interestLectureRepository
                .findInterestLectureByMemberIdAndLecture_LectureId(member.getMemberId(), lectureId)
                .orElseGet(InterestLecture::new);

        boolean isInterested = !interestLecture.isCanceled() && interestLecture.getId() != null;

        return new FindInterestResponse(isInterested);
    }

    //TODO: 포스트맨 쓰는 싸가지들 처리  (좋아요 중복 처리) 반환 dto없애고 객체 없애고 save 예외처리
    @Transactional
    public FindInterestResponse createInterest(String lectureId, LoggedInMember loggedInMember) {
        Lecture lecture = lectureService.getLectureDetail(lectureId);
        Member member = memberService.findMemberByMemberName(loggedInMember.getMemberName());

        Optional<InterestLecture> optionalInterestLecture = interestLectureRepository
                .findInterestLectureByMemberIdAndLecture_LectureId(member.getId(), lectureId);

        if(optionalInterestLecture.isEmpty()){
            interestLectureRepository.save(InterestLecture.builder()
                    .member(member)
                    .lecture(lecture)
                    .build());
            return new FindInterestResponse(true);
        }else{
            InterestLecture interestLecture = optionalInterestLecture.get();
            if (interestLecture.isCanceled()) {
                interestLecture.updateIsCanceled(false);
                return new FindInterestResponse(true);
            }
            interestLecture.updateIsCanceled(true);
            return new FindInterestResponse(false);
        }
    }

}
