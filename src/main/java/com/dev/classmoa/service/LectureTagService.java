package com.dev.classmoa.service;

import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.entity.LectureTag;
import com.dev.classmoa.domain.repository.LectureTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureTagService {

    private final LectureTagRepository lectureTagRepository;
    public List<Lecture> getLectureListByTag(Long tagId) {
        List<LectureTag> lectureTags = lectureTagRepository.findLectureTagByTagId(tagId);

        return lectureTags.stream()
                .map(LectureTag::getLecture)
                .toList();
    }

}
