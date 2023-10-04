package com.dev.classmoa.service;

import com.dev.classmoa.domain.repository.LectureTagRepository;
import com.dev.classmoa.dto.Lecture.response.FindLectureResponse;
import org.assertj.core.api.Assertions;
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

    @Test
    @DisplayName("태그로 강의리스트 조회하기")
    void getLectureListByTag() {
        // given
        Long tagId = 1L;

        // when
        List<FindLectureResponse> lectures = lectureTagService.getLectureListByTag(tagId);

        // then
        assertThat(lectures.size()).isEqualTo(2);
    }
}