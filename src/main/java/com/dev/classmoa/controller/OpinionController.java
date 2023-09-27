package com.dev.classmoa.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.dto.comment.request.CreateComment;
import com.dev.classmoa.dto.comment.request.DeleteComment;
import com.dev.classmoa.dto.comment.request.EditComment;
import com.dev.classmoa.dto.opinion.request.CreateOpinion;
import com.dev.classmoa.dto.opinion.request.DeleteOpinion;
import com.dev.classmoa.dto.opinion.request.EditOpinion;
import com.dev.classmoa.dto.opinion.response.FindOpinion;
import com.dev.classmoa.service.OpinionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OpinionController {
    private final OpinionService opinionService;

    // 의견리스트 조회 + 댓글 조회
    @GetMapping("/lecture/{lectureId}/opinions")
    public ResponseEntity<List<FindOpinion>> getOpinions(@PathVariable String lectureId){
        List<FindOpinion> opinions = opinionService.getOpinions(lectureId)
                .stream().map(FindOpinion::new).toList();
        return ResponseEntity.ok(opinions);
    }

    //TODO: PathVariable << 여기
    // 의견 등록
    @PostMapping("/user/lecture/{lecture_id}/opinion")
    public Long createOpinion(CreateOpinion createOpinion, @PathVariable("lecture_id") String lectureId, Member member){
        return opinionService.create(createOpinion.toEntity(), lectureId, member);
    }

    // 의견 수정
    @PostMapping("/user/lecture/opinion")
    public Boolean editOpinion(EditOpinion editOpinion, Member member){
        return opinionService.edit(editOpinion.toEntity(), member);
    }

    // 의견 삭제
    @DeleteMapping("/user/lecture/opinion")
    public void deleteOpinion(DeleteOpinion deleteOpinion, Member member){
        opinionService.delete(deleteOpinion.toEntity(), member);
    }

    // 댓글 등록
    @PostMapping("/user/opinion/comment/")
    public Long createComment(CreateComment createComment, Member member){
        return opinionService.commentCreate(createComment.toEntity(), member);
    }
    // 댓글 수정
    @PostMapping("/user/opinion/comment")
    public Boolean editComment(EditComment editComment, Member member){
        return opinionService.commentEdit(editComment.toEntity(), member);
    }

    // 댓글 삭제
    @DeleteMapping("/user/opinion/comment")
    public Boolean deleteComment(DeleteComment deleteComment, Member member){
        return opinionService.commentDelete(deleteComment.toEntity(), member);
    }
}