package com.dev.classmoa.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dev.classmoa.exception.advice.ValidationExceptionHandler;
import com.dev.classmoa.service.LectureTagService;
import com.dev.classmoa.service.MemberService;

@WebMvcTest(LectureTagController.class)
@AutoConfigureMockMvc
@MockBean(JpaMetamodelMappingContext.class)
class LectureTagControllerTest {

	@Autowired
	MockMvc mvc;

	@Autowired
	LectureTagController controller;

	@MockBean
	LectureTagService lectureTagService;

	@MockBean
	MemberService memberService;

	@Test
	void test() throws Exception {


		mvc.perform(get("/tag/-1"))
			.andDo(print());
	}
}