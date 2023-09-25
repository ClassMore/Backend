package com.example.classmoa.service;

import com.example.classmoa.domain.entity.Lecture;
import com.example.classmoa.domain.repository.LectureRepository;
import com.example.classmoa.dto.Lecture.response.FindLecture;
import com.example.classmoa.dto.Lecture.response.FindLectureDetail;
import com.example.classmoa.exception.LectureCode;
import com.example.classmoa.exception.ClassmoaException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;

    public List<Lecture> getLectureList(Pageable pageable) {
        return lectureRepository.findAll(pageable).getContent();
    }

    public Lecture getLectureDetail(String lectureId) {
        return lectureRepository.finByLectureId(lectureId);
    }

//    public Lecture getLectureListByTag(Long id) {
//        return lectureRepository.findByTag(id)
//                .orElseThrow(() -> new ClassmoaException(LectureCode.NOT_FOUND_LECTURE));
//    }
}
