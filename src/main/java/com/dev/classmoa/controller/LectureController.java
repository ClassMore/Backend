package com.dev.classmoa.controller;

import com.dev.classmoa.dto.Lecture.response.FindLecture;
import com.dev.classmoa.dto.Lecture.response.FindLectureDetail;
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

    @GetMapping("/")
    public ResponseEntity<List<FindLecture>> getLectures(Pageable pageable) {
        List<FindLecture> lectures = lectureService.getLectureList(pageable)
                .stream().map(FindLecture::new).toList();
        return ResponseEntity.ok(lectures);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<List<FindLecture>> getLecturesByTag(Pageable pageable) {
//        return ResponseEntity.ok(new FindLectureDetail(lectureService.getLectureDetail(id)));
//    }

    //TODO: PathVariable
    @GetMapping("/lecture/{id}")
    public ResponseEntity<FindLectureDetail> getLecture(@PathVariable String lectureId) {
        return ResponseEntity.ok(new FindLectureDetail(lectureService.getLectureDetail(lectureId)));
    }

}
