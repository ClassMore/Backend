package com.dev.classmoa.controller;

import com.dev.classmoa.dto.Lecture.response.FindLecture;
import com.dev.classmoa.service.LectureTagService;
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

    @GetMapping("/{id}")
    public ResponseEntity<List<FindLecture>> getLecturesByTag(@PathVariable Long tagId) {
        return ResponseEntity.ok(lectureTagService.getLectureListByTag(tagId));
    }

}
