package com.example.classmoa.service;

import com.example.classmoa.domain.entity.Lecture;
import com.example.classmoa.domain.entity.Member;
import com.example.classmoa.domain.entity.Opinion;
import com.example.classmoa.domain.repository.LectureRepository;
import com.example.classmoa.domain.repository.OpinionRepository;
import com.example.classmoa.dto.opinion.response.FindOpinion;
import com.example.classmoa.exception.ClassmoaException;
import com.example.classmoa.exception.LectureCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OpinionService {
    private final OpinionRepository opinionRepository;
    private final LectureService lectureService;

    public List<Opinion> getOpinions(String lectureId) {
        Lecture lecture = lectureService.getLectureDetail(lectureId);
        return lecture.getOpinions();
    }

    public Long create(Opinion newOpinion, String lectureId, Member member){
        Lecture lecture = lectureService.getLectureDetail(lectureId);
        return opinionRepository.save(
                Opinion.builder()
                        .content(newOpinion.getContent())
                        .isModified(false)
                        .member(member)
                        .lecture(lecture)
                        .build()
        ).getId();
    }

    // Member 객체가 굳이 필요하지 않을거 같음.
    public Boolean edit(Opinion opinion){
        Opinion savedOpinion = opinionRepository.findById(opinion.getId())
            .orElseThrow(() -> new IllegalArgumentException("not found"));

        return opinionRepository.save(
            savedOpinion.builder()
                    .isModified(true)
                    .content(opinion.getContent())
                    .build()
        ).getIsModified();
    }

    // Member 객체가 굳이 필요하지 않을거 같음.
    public void delete(Opinion opinion){
        opinionRepository.deleteById(opinion.getId());
    }
}
