package com.dev.classmoa.service;

import com.dev.classmoa.domain.entity.Alarm;
import com.dev.classmoa.domain.entity.InterestLecture;
import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.repository.InterestLectureRepository;
import com.dev.classmoa.domain.repository.LectureRepository;
import com.dev.classmoa.domain.repository.MemberRepository;
import com.dev.classmoa.dto.interest.response.FindInterestLecturesResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @AfterEach
    public void init(){

    }

    @BeforeEach
    void CreateTestData() {
        Lecture lecture1 = Lecture.builder()
                .companyName("카클스")
                .imageLink("어딘가 있음")
                .lectureId("카클스1")
                .instructor("임창준")
                .ordinaryPrice(100000)
                .salePercent("30%")
                .salePrice(70000)
                .siteLink("www.ybm.com")
                .title("오사카 여행 가이드")
                .build();

        Lecture lecture2 = Lecture.builder()
                .lectureId("카클스2")
                .companyName("카클스")
                .imageLink("어딘가 있음")
                .instructor("임창준")
                .ordinaryPrice(100000)
                .salePercent("30%")
                .salePrice(70000)
                .siteLink("www.ybm.com")
                .title("오사카 여행 가이드")
                .build();

        Lecture lecture3 = Lecture.builder()
                .lectureId("카클스3")
                .companyName("카클스")
                .imageLink("임창준 아이폰")
                .instructor("임창준")
                .ordinaryPrice(100000)
                .salePercent("30%")
                .salePrice(70000)
                .siteLink("www.창준교토.gg")
                .title("교토 여행 가이드")
                .build();

        lectureRepository.save(lecture1);
        lectureRepository.save(lecture2);
        lectureRepository.save(lecture3);


        Member member = Member.signup()
                .email("123@gmail.com")
                .password("123")
                .nickname("동그리")
                .signupbuild();

        memberRepository.save(member);

        InterestLecture interestLecture1 = InterestLecture.builder()
                .member(member)
                .lecture(lecture1)
                .build();

        InterestLecture interestLecture2 = InterestLecture.builder()
                .member(member)
                .lecture(lecture2)
                .build();

        interestLectureRepository.save(interestLecture1);
        interestLectureRepository.save(interestLecture2);
    }

    @Test
    @DisplayName("lecture 정보와 회원 정보를 받아서 강의 좋아요를 등록 한다.")
    void createInterest() {
        //given
        Long memberId = 1L;
        Member member = memberRepository.findById(1L).get();
        Lecture lecture = lectureRepository.findByLectureIdAndDate("카클스3", LocalDate.now());

        //when
        interestService.createInterest(lecture.getLectureId(), member);

        //then
        List<InterestLecture> interestLectures = interestLectureRepository
                .findInterestLecturesByMemberId(memberId);
        assertThat(interestLectures.get(0).getMember().getId()).isEqualTo(memberId);
    }

    @Test
    @DisplayName("회원 정보를 받아서 좋아요 내역을 조회 한다.")
    void getInterestList() {
        // given
        Long memberId = 1L;

        // when
        List<FindInterestLecturesResponse> interestList = interestService
                .getInterestListByMember(memberId);

        // then
        assertThat(interestList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("interestId와 회원 정보를 받아서 좋아요를 취소 한다.")
    void cancelInterest() {
        // given
        Long interestId = 2L;
        Member member = memberRepository.findById(1L).get();

        // when
        interestService.cancelInterest(interestId, member);

        // then
        assertThat(interestLectureRepository.findById(interestId).isEmpty())
                .isTrue();
    }
}