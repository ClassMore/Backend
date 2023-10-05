package com.dev.classmoa.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.dto.comment.request.CreateCommentRequest;
import com.dev.classmoa.dto.comment.request.DeleteCommentRequest;
import com.dev.classmoa.dto.comment.request.EditCommentRequest;
import com.dev.classmoa.dto.comment.response.DeleteCommentResponse;
import com.dev.classmoa.dto.comment.response.EditCommentResponse;
import com.dev.classmoa.dto.opinion.request.CreateOpinionRequest;
import com.dev.classmoa.dto.opinion.request.DeleteOpinionRequest;
import com.dev.classmoa.dto.opinion.request.EditOpinionRequest;
import com.dev.classmoa.dto.opinion.response.EditOpinionResponse;
import com.dev.classmoa.dto.opinion.response.FindOpinionResponse;
import com.dev.classmoa.service.OpinionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OpinionController {
    private final OpinionService opinionService;

    // 의견리스트 조회 + 댓글 조회
    @GetMapping("/lecture/{lectureId}/opinions")
    public ResponseEntity<List<FindOpinionResponse>> getOpinions(@PathVariable String lectureId){
        List<FindOpinionResponse> opinions = opinionService.getOpinions(lectureId)
                .stream().map(FindOpinionResponse::new).toList();
        return ResponseEntity.ok(opinions);
    }

    //TODO: PathVariable << 여기
    // 의견 등록
    @PostMapping("/user/lecture/{lecture_id}/opinion")
    public void createOpinion(@RequestBody @Valid CreateOpinionRequest createOpinion, @PathVariable("lecture_id") String lectureId, Member member){
        opinionService.createOpinion(createOpinion, lectureId, member);
    }

    // 의견 수정
    @PostMapping("/user/lecture/opinion")
    public ResponseEntity<EditOpinionResponse> editOpinion(@RequestBody @Valid EditOpinionRequest editOpinion, Member member){
        return ResponseEntity.ok(opinionService.editOpinion(editOpinion, member));
    }

    // 의견 삭제
    @DeleteMapping("/user/lecture/opinion")
    public ResponseEntity<Void> deleteOpinion(@RequestBody @Valid DeleteOpinionRequest deleteOpinion, Member member){
        opinionService.deleteOpinion(deleteOpinion, member);
        return ResponseEntity.ok().build();
    }

    // 댓글 등록
    @PostMapping("/user/opinion/{opinion_id}/comment")
    public void createComment(@RequestBody @Valid CreateCommentRequest createComment, @PathVariable("opinion_id") @Valid Long opinionId, Member member){
        opinionService.commentCreate(createComment, opinionId, member);
    }
    // 댓글 수정
    @PostMapping("/user/opinion/comment")
    public ResponseEntity<EditCommentResponse> editComment(@RequestBody @Valid EditCommentRequest editComment, Member member){
        return ResponseEntity.ok(opinionService.commentEdit(editComment, member));
    }

    // 댓글 삭제
    @DeleteMapping("/user/opinion/comment")
    public ResponseEntity<DeleteCommentResponse> deleteComment(@RequestBody @Valid DeleteCommentRequest deleteComment, Member member){
        return ResponseEntity.ok(opinionService.commentDelete(deleteComment, member));
    }
}