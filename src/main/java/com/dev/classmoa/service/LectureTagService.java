package com.dev.classmoa.service;

import com.dev.classmoa.domain.entity.LectureTag;
import com.dev.classmoa.domain.repository.LectureTagRepository;
import com.dev.classmoa.dto.Lecture.response.FindLectureListResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureTagService {

    private final LectureTagRepository lectureTagRepository;
    public List<FindLectureListResponse> getLectureListByTag(Long tagId) {
        List<LectureTag> lectureTags = lectureTagRepository.findLectureTagsByTagId(tagId);

        return lectureTags.stream()
                .map(LectureTag::getLecture)
                .map(FindLectureListResponse::new)
                .toList();
    }

}
