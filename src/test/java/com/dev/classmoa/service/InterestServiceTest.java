package com.dev.classmoa.service;

import com.dev.classmoa.domain.entity.InterestLecture;
import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.repository.InterestLectureRepository;
import com.dev.classmoa.domain.repository.LectureRepository;
import com.dev.classmoa.domain.repository.MemberRepository;
import com.dev.classmoa.dto.Lecture.response.FindInterestLecturesResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class InterestServiceTest {
    @Autowired
    InterestService interestService;

    @Autowired
    InterestLectureRepository interestLectureRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LectureRepository lectureRepository;

    @Test
    @DisplayName("강의 좋아요를 등록한다")
    void createInterest() {
        //given
        Long memberId = 1L;
        Member member = memberRepository.findById(1L).get();
        Lecture lecture = lectureRepository.findByLectureIdAndDate("artstudyjwLee508", LocalDate.now());

        //when
        interestService.create(lecture.getLectureId(), member);

        //then
        List<InterestLecture> interestLectures = interestLectureRepository
                .findInterestLecturesByMemberId(memberId);
        assertThat(interestLectures.get(0).getMember().getId()).isEqualTo(memberId);
    }

    @Test
    @DisplayName("좋아요 내역을 조회한다")
    void getInterestList() {
        // given
        Member member = memberRepository.findById(1L).get();

        // when
        List<FindInterestLecturesResponse> interestList = interestService
                .getLectureListByMember(member.getId());

        // then
        assertThat(interestList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("좋아요를 취소한다")
    void cancelInterest() {
        // given
        Long interestId = 2L;
        Member member = memberRepository.findById(1L).get();

        // when
        interestService.cancel(interestId, member);

        // then
        assertThat(interestLectureRepository.findById(interestId).isEmpty())
                .isTrue();
    }
}