package com.dev.classmoa.service;

import com.dev.classmoa.domain.entity.Tag;
import com.dev.classmoa.domain.repository.TagRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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