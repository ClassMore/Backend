package com.dev.classmoa.controller;

import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.dto.Lecture.response.FindLectureDetailResponse;
import com.dev.classmoa.dto.Lecture.response.FindLectureListResponse;
import com.dev.classmoa.service.LectureService;
import com.dev.classmoa.service.ViewCountService;
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
    private final ViewCountService viewCountService;

    @GetMapping("/lecture")
    public ResponseEntity<List<FindLectureListResponse>> getLectures(Pageable pageable) {
        return ResponseEntity.ok(lectureService.getLectureList(pageable));
    }

    //TODO: PathVariable
    @GetMapping("/lecture/{lecture_id}")
    public ResponseEntity<FindLectureDetailResponse> getLecture(@PathVariable("lecture_id") String lectureId) {
        Lecture lecture = lectureService.getLectureDetail(lectureId);
        FindLectureDetailResponse response = new FindLectureDetailResponse(lecture);
        response.setViewCount(viewCountService.countViewCountUp(lecture));
        return ResponseEntity.ok(response);
    }
}
