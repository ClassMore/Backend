package com.dev.classmoa.service;

import com.dev.classmoa.domain.entity.*;
import com.dev.classmoa.domain.repository.LectureRepository;
import com.dev.classmoa.domain.repository.LectureTagRepository;
import com.dev.classmoa.domain.repository.TagRepository;
import com.dev.classmoa.dto.Lecture.response.FindLectureListResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class LectureTagServiceTest {
    @Autowired
    LectureTagService lectureTagService;

    @Autowired
    LectureTagRepository lectureTagRepository;

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    TagRepository tagRepository;

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

        Tag tag1 = Tag.builder()
                .name("태그1")
                .build();

        Tag tag2 = Tag.builder()
                .name("태그2")
                .build();

        tagRepository.save(tag1);
        tagRepository.save(tag2);

        LectureTag lectureTag1 = LectureTag.builder()
                .lecture(lecture1)
                .tag(tag1)
                .build();

        LectureTag lectureTag2 = LectureTag.builder()
                .lecture(lecture2)
                .tag(tag1)
                .build();

        LectureTag lectureTag3 = LectureTag.builder()
                .lecture(lecture2)
                .tag(tag2)
                .build();

        lectureTagRepository.save(lectureTag1);
        lectureTagRepository.save(lectureTag2);
        lectureTagRepository.save(lectureTag3);

    }

    @Test
    @DisplayName("tagId를 받아서 태그로 강의 목록을 조회 한다.")
    void getLectureListByTag() {
        // given
        Long tagId = 1L;

        // when
        List<FindLectureListResponse> lectures = lectureTagService.getLectureListByTag(tagId);

        // then
        assertThat(lectures.get(0).getId()).isEqualTo(1);
    }
}