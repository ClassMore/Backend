package com.dev.classmoa.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.entity.Opinion;
import com.dev.classmoa.domain.repository.OpinionRepository;
import com.dev.classmoa.dto.opinion.request.CreateOpinion;
import com.dev.classmoa.dto.opinion.request.EditOpinion;
import com.dev.classmoa.dto.opinion.response.FindOpinion;
import com.dev.classmoa.service.OpinionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OpinionController {
    private final OpinionService opinionService;
    private final OpinionRepository opinionRepository;

    // 의견리스트 조회
    @GetMapping("/lecture/{lectureId}/opinions")
    public ResponseEntity<List<FindOpinion>> getOpinions(@PathVariable String lectureId){
        List<FindOpinion> lectures = opinionService.getOpinions(lectureId)
                .stream().map(FindOpinion::new).toList();
        return ResponseEntity.ok(lectures);
    }

    // 의견 등록
    @PostMapping("/lecture/{lectureId}/opinion")
    public Long createOpinion(CreateOpinion createOpinion, @PathVariable String lectureId, Member member){
        return opinionService.create(createOpinion.toEntity(), lectureId, member);
    }

    // 의견 수정
    @PostMapping("/lecture/{lectureId}/opinion")
    public Boolean editOpinion(EditOpinion editOpinion){
        return opinionService.edit(editOpinion.toEntity());
    }

    // 의견 삭제
    @DeleteMapping("/user/lecture/{lecture_id}/opinion")
    public void deleteOpinion(Opinion opinion){
        opinionService.delete(opinion);
    }

    // 댓글 등록

    // 댓글 수정

    // 댓글 삭제
}