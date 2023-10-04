package com.dev.classmoa.service;

import com.dev.classmoa.domain.entity.Tag;
import com.dev.classmoa.domain.repository.TagRepository;
import com.dev.classmoa.dto.Lecture.response.FindLectureResponse;
import com.dev.classmoa.dto.tag.response.FindTagResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TagServiceTest {

    @Autowired
    TagService tagService;
    
    @Autowired
    TagRepository tagRepository;

    @Test
    @DisplayName("태그 리스트를 가져온다.")
    void getTagList(){
        // given
        Pageable pageable = PageRequest.of(0, 10);

        // when
        List<Tag> tags = tagService.getTagList(pageable);

        // then
        assertThat(tags.size()).isEqualTo(2);
    }
}