package com.dev.classmoa.service;

import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.repository.LectureRepository;
import com.dev.classmoa.dto.Lecture.response.FindLectureListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;

    public List<FindLectureListResponse> getLectureList(Pageable pageable) {
        List<Lecture> lectures = lectureRepository.findAllByDate(pageable, LocalDate.now()).getContent();
        return lectures.stream()
                .map(FindLectureListResponse::new)
                .toList();
    }

    // TODO: lecture_id 로 찾으면 예전에 받은 강의들까지 싹다 받아 올 것 같은데 [가영]
    public Lecture getLectureDetail(String lectureId) {
        Lecture findedLecture = lectureRepository.findByLectureIdAndDate(lectureId, LocalDate.now());
        return findedLecture;
    }
}
