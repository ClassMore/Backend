package com.dev.classmoa.controller;

import com.dev.classmoa.domain.repository.InterestLectureRepository;
import com.dev.classmoa.domain.repository.OpinionRepository;
import com.dev.classmoa.exception.ClassmoaException;
import com.dev.classmoa.exception.type.ClassmoaErrorCode;
import com.dev.classmoa.service.InterestService;
import com.dev.classmoa.service.OpinionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OpinionController.class)
@AutoConfigureMockMvc
@MockBean(JpaMetamodelMappingContext.class)
class OpinionControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    OpinionService opinionService;

    @MockBean
    OpinionRepository opinionRepository;

    @MockBean
    ClassmoaErrorCode classmoaErrorCode;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("존재 하지 않는 강의의 의견과 댓글을 조회할 수 없습니다.")
    void getOpinionsTest() throws Exception{
        //given
        given(opinionService.getOpinions(eq("lectureId")))
                .willThrow(new ClassmoaException(ClassmoaErrorCode.NOT_FOUND_LECTURE));

        //expected
        mvc.perform(get("/lecture/lectureId/opinions"))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("존재 하지 않는 강의의 의견을 수정할 수 없습니다.")
    void editOpinionsTest() throws Exception{
        //given
        given(opinionService.editOpinion(any(), any()))
                .willThrow(new ClassmoaException(ClassmoaErrorCode.NOT_FOUND_OPINION));

        Map<String, Object> content = Map.of("id", 1L, "content", "content");

        //expected
        mvc.perform(put("/user/lecture/edit/opinion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(content)))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("존재 하지 않는 강의의 의견을 삭제할 수 없습니다.")
    void deleteOpinionsTest() throws Exception{
        //given
        given(opinionService.deleteOpinion(any(), any()))
                .willThrow(new ClassmoaException(ClassmoaErrorCode.NOT_FOUND_OPINION));

        Map<String, Object> content = Map.of("isDeleted", "false");

        //expected
        mvc.perform(delete("/user/lecture/delete/opinion")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(content)))
                    .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("존재 하지 않는 의견에 댓글을 등록할 수 없습니다.")
    void createCommentsTest() throws Exception{
        //given
        given(opinionService.commentCreate(any(), eq(1L), any()))
                .willThrow(new ClassmoaException(ClassmoaErrorCode.NOT_FOUND_OPINION));

        Map<String, Object> content = Map.of("opinionId", 1L, "content", "content");

        //expected
        mvc.perform(post("/user/opinion/1/comment")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(content)))
                    .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("존재 하지 않는 강의의 댓글을 수정할 수 없습니다.")
    void editCommentsTest() throws Exception{
        //given
        given(opinionService.commentEdit(any(), any()))
                .willThrow(new ClassmoaException(ClassmoaErrorCode.NOT_FOUND_COMMENT));

        Map<String, Object> content = Map.of("id", 1L, "content", "content");

        //expected
        mvc.perform(put("/user/opinion/edit/comment")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(content)))
                    .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("존재 하지 않는 강의의 댓글을 삭제할 수 없습니다.")
    void deleteCommentsTest() throws Exception{
        //given
        given(opinionService.commentDelete(any(), any()))
                .willThrow(new ClassmoaException(ClassmoaErrorCode.NOT_FOUND_COMMENT));

        Map<String, Object> content = Map.of("isDeleted", "false");

        //expected
        mvc.perform(delete("/user/opinion/delete/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(content)))
                .andDo(print()).andExpect(status().isNotFound());
    }
}