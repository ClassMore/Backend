package com.dev.classmoa.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.repository.LectureRepository;

@SpringBootTest
class OpinionServiceTest {

	@Autowired
	OpinionService opinionService;

	@Test
	void createComments(){
		// opinionService.create()
	}
}