package com.dev.classmoa.controller;

import com.dev.classmoa.dto.Lecture.response.FindLectureListResponse;
import com.dev.classmoa.service.LectureTagService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LectureTagController {
    private final LectureTagService lectureTagService;

    //TODO: PathVariable
    @GetMapping("/{id}")
    public ResponseEntity<List<FindLectureListResponse>> getLecturesByTag(@PathVariable @Valid Long tagId) {
        return ResponseEntity.ok(lectureTagService.getLectureListByTag(tagId));
    }

}
