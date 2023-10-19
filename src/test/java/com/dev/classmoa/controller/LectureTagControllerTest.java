package com.dev.classmoa.controller;

import com.dev.classmoa.domain.repository.LectureTagRepository;
import com.dev.classmoa.exception.ClassmoaException;
import com.dev.classmoa.exception.type.ClassmoaErrorCode;
import com.dev.classmoa.service.LectureTagService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(LectureTagController.class)
@AutoConfigureMockMvc(addFilters = false)
@MockBean(JpaMetamodelMappingContext.class)
class LectureTagControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    LectureTagController lectureTagController;

    @MockBean
    LectureTagService lectureTagService;

    @MockBean
    LectureTagRepository lectureTagRepository;

//    @Test
//    @DisplayName("존재 하지 않는 태그 아이디 입니다.")
//    void getLectureTagsTest() throws Exception {
//        //given
//        given(lectureTagController.getLecturesByTag(1L))
//                .willThrow(new ConstraintViolationException("ljjnj"));
//
//        //expected
//        mvc.perform(get("/{id}", -1))
//                .andExpect(status().isBadRequest());
//    }

}