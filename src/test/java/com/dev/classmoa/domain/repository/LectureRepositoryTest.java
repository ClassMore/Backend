package com.dev.classmoa.domain.repository;

import com.dev.classmoa.domain.entity.Lecture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LectureRepositoryTest {
    @Autowired
    LectureRepository lectureRepository;

}