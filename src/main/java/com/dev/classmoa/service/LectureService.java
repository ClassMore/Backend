package com.dev.classmoa.service;

import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.repository.LectureRepository;

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
    
    // TODO: lecture_id 로 찾으면 예전에 받은 강의들까지 싹다 받아 올 것 같은데
    public Lecture getLectureDetail(String lectureId) {
        return lectureRepository.findByLectureId(lectureId);
    }

}
