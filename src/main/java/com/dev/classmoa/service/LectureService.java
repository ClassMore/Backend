package com.dev.classmoa.service;

import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.repository.LectureRepository;
import com.dev.classmoa.dto.Lecture.response.FindLectureListResponse;
import com.dev.classmoa.exception.ClassmoaException;
import com.dev.classmoa.exception.type.ClassmoaErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
   private final ViewCountService viewCountService;

    public List<FindLectureListResponse> getLectureList(Pageable pageable) {
        List<Lecture> lectures = lectureRepository.findAllByDate(pageable, LocalDate.now()).getContent();
        return lectures.stream()
                .map(FindLectureListResponse::new)
                .toList();
    }

    // TODO: lecture_id 로 찾으면 예전에 받은 강의들까지 싹다 받아 올 것 같은데 [가영]
    @Transactional
    public Lecture getLectureDetail(String lectureId) {
        Lecture lecture = lectureRepository.findByLectureIdAndDate(lectureId, LocalDate.now())
                .orElseThrow(() -> new ClassmoaException(ClassmoaErrorCode.NOT_FOUND_LECTURE));
        viewCountService.viewCountUp(lecture);
        return lecture;
    }
}
