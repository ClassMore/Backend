package com.dev.classmoa.controller;

import com.dev.classmoa.dto.Lecture.response.FindLectureDetailResponse;
import com.dev.classmoa.dto.Lecture.response.FindLectureResponse;
import com.dev.classmoa.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LectureController {
    private final LectureService lectureService;

    @GetMapping("/lecture")
    public ResponseEntity<List<FindLectureResponse>> getLectures(Pageable pageable) {
        return ResponseEntity.ok(lectureService.getLectureList(pageable));
    }

    //TODO: PathVariable
    @GetMapping("/lecture/{lecture_id}")
    public ResponseEntity<FindLectureDetailResponse> getLecture(@PathVariable("lecture_id") String lectureId) {
        return ResponseEntity.ok(new FindLectureDetailResponse(lectureService.getLectureDetail(lectureId)));
    }

}
