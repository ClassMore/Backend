package com.dev.classmoa.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.dto.comment.request.CreateCommentRequest;
import com.dev.classmoa.dto.comment.request.DeleteCommentRequest;
import com.dev.classmoa.dto.comment.request.EditCommentRequest;
import com.dev.classmoa.dto.comment.response.CreateCommentResponse;
import com.dev.classmoa.dto.comment.response.DeleteCommentResponse;
import com.dev.classmoa.dto.comment.response.EditCommentResponse;
import com.dev.classmoa.dto.opinion.request.CreateOpinionRequest;
import com.dev.classmoa.dto.opinion.request.DeleteOpinionRequest;
import com.dev.classmoa.dto.opinion.request.EditOpinionRequest;
import com.dev.classmoa.dto.opinion.response.CreateOpinionResponse;
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
    public ResponseEntity<CreateOpinionResponse> createOpinion(CreateOpinionRequest createOpinion, @PathVariable("lecture_id") String lectureId, Member member){
        return ResponseEntity.ok(opinionService.createOpinion(createOpinion.toEntity(), lectureId, member));
    }

    // 의견 수정
    @PostMapping("/user/lecture/opinion")
    public ResponseEntity<EditOpinionResponse> editOpinion(EditOpinionRequest editOpinion, Member member){
        return ResponseEntity.ok(opinionService.editOpinion(editOpinion.toEntity(), member));
    }

    // 의견 삭제
    @DeleteMapping("/user/lecture/opinion")
    public ResponseEntity<Void> deleteOpinion(DeleteOpinionRequest deleteOpinion, Member member){
        opinionService.deleteOpinion(deleteOpinion.toEntity(), member);
        return ResponseEntity.ok().build();
    }

    // 댓글 등록
    @PostMapping("/user/opinion/{opinion_id}/comment")
    public ResponseEntity<CreateCommentResponse> createComment(CreateCommentRequest createComment, @PathVariable("opinion_id") Long opinionId, Member member){
        return ResponseEntity.ok(opinionService.commentCreate(createComment.toEntity(), opinionId, member));
    }
    // 댓글 수정
    @PostMapping("/user/opinion/comment")
    public ResponseEntity<EditCommentResponse> editComment(EditCommentRequest editComment, Member member){
        return ResponseEntity.ok(opinionService.commentEdit(editComment.toEntity(), member));
    }

    // 댓글 삭제
    @DeleteMapping("/user/opinion/comment")
    public ResponseEntity<DeleteCommentResponse> deleteComment(DeleteCommentRequest deleteComment, Member member){
        return ResponseEntity.ok(opinionService.commentDelete(deleteComment.toEntity(), member));
    }
}