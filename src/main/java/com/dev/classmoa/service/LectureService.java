package com.dev.classmoa.service;

import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.repository.LectureRepository;
import com.dev.classmoa.dto.Lecture.response.FindLectureDetailResponse;
import com.dev.classmoa.dto.Lecture.response.FindLectureResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;

    public List<FindLectureResponse> getLectureList(Pageable pageable) {
        List<Lecture> lectures = lectureRepository.findAll(pageable).getContent();
        return lectures.stream()
            .map(FindLectureResponse::new)
            .toList();
    }
    
    // TODO: lecture_id 로 찾으면 예전에 받은 강의들까지 싹다 받아 올 것 같은데 [가영]
    public Lecture getLectureDetail(String lectureId) {
        Lecture findedLecture = lectureRepository.findByLectureId(lectureId);
        return findedLecture;
    }
}
