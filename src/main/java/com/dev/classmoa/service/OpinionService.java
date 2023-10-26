package com.dev.classmoa.service;

import com.dev.classmoa.auth.annotation.LoginMember;
import com.dev.classmoa.domain.entity.Comment;
import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.entity.Opinion;
import com.dev.classmoa.domain.repository.CommentRepository;
import com.dev.classmoa.domain.repository.OpinionRepository;
import com.dev.classmoa.dto.Member.LoggedInMember;
import com.dev.classmoa.dto.comment.request.CreateCommentRequest;
import com.dev.classmoa.dto.comment.request.DeleteCommentRequest;
import com.dev.classmoa.dto.comment.request.EditCommentRequest;
import com.dev.classmoa.dto.comment.response.DeleteCommentResponse;
import com.dev.classmoa.dto.comment.response.EditCommentResponse;
import com.dev.classmoa.dto.opinion.request.CreateOpinionRequest;
import com.dev.classmoa.dto.opinion.request.DeleteOpinionRequest;
import com.dev.classmoa.dto.opinion.request.EditOpinionRequest;
import com.dev.classmoa.dto.opinion.response.DeleteOpinionResponse;
import com.dev.classmoa.dto.opinion.response.EditOpinionResponse;

import com.dev.classmoa.exception.ClassmoaException;
import com.dev.classmoa.exception.type.ClassmoaErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpinionService {

    private final OpinionRepository opinionRepository;
    private final LectureService lectureService;
    private final CommentRepository commentRepository;
    private final MemberService memberService;

    public List<Opinion> getOpinions(String lectureId) {
        try {
            return opinionRepository.getOpinions(lectureId);
        } catch (ClassmoaException ex) {
            throw new ClassmoaException(ClassmoaErrorCode.NOT_FOUND_LECTURE);
        }
    }

    //TODO: 반환 없고, dto?
    public void createOpinion(CreateOpinionRequest newOpinion, String lectureId,
                              LoggedInMember loggedInMember){
        Lecture lecture = lectureService.getLectureDetail(lectureId);
        Member member = memberService.findMemberById(loggedInMember.getMemberId());
        opinionRepository.save(
                Opinion.builder()
                        .content(newOpinion.getContent())
                        .member(member)
                        .lecture(lecture)
                        .build()
        );
    }

    // TODO: 업데이트라면 save 할 필요 없을 것 같은데, Setter 역할을 하는 메소드를 하나 만들자. [창준] 멤버 다를때 예외???
    @Transactional
    public EditOpinionResponse editOpinion(EditOpinionRequest opinion,
                                           LoggedInMember loggedInMember){
        Opinion savedOpinion = opinionRepository.getOpinion(opinion.getLectureId(), opinion.getId())
            .orElseThrow(() -> new ClassmoaException(ClassmoaErrorCode.NOT_FOUND_OPINION));
        Member member = memberService.findMemberById(loggedInMember.getMemberId());

        if(savedOpinion.getMember().equals(member)){
            savedOpinion.editOpinion(opinion.getContent());
            return new EditOpinionResponse(true);
        }
        return new EditOpinionResponse(false);
    }

    // TODO: 예외 처리 함수
    @Transactional
    public DeleteOpinionResponse deleteOpinion(DeleteOpinionRequest opinion,
                                               LoggedInMember loggedInMember) {
        Opinion savedOpinion = opinionRepository.getOpinion(opinion.getLectureId(), opinion.getId())
            .orElseThrow(() -> new ClassmoaException(ClassmoaErrorCode.NOT_FOUND_OPINION));
        Member member = memberService.findMemberById(loggedInMember.getMemberId());

        if(savedOpinion.getMember().equals(member)) {
            savedOpinion.deleteOpinion(true);
            return new DeleteOpinionResponse(true);
        }
        return new DeleteOpinionResponse(false);
    }

    // 댓글 생성
    //TODO: ???? [가영] 반환값???
    public Void commentCreate(CreateCommentRequest newComment, @LoginMember LoggedInMember loggedInMember){
        Opinion opinion = opinionRepository.getOpinion(newComment.getLectureId(), newComment.getOpinionId())
                .orElseThrow(() -> new ClassmoaException(ClassmoaErrorCode.NOT_FOUND_OPINION));

        Member member = memberService.findMemberById(loggedInMember.getMemberId());
        commentRepository.save(
                Comment.builder()
                        .content(newComment.getContent())
                        .member(member)
                        .opinion(opinion)
                        .build()
        );
        return null;
    }

    @Transactional
    public EditCommentResponse commentEdit(EditCommentRequest newComment,
                                           @LoginMember LoggedInMember loggedInMember){
        Comment comment = commentRepository.findById(newComment.getId())
            .orElseThrow(() -> new ClassmoaException(ClassmoaErrorCode.NOT_FOUND_COMMENT));
        Member member = memberService.findMemberById(loggedInMember.getMemberId());

        if(comment.getMember().equals(member)){
            comment.editComment(newComment.getContent());
            return new EditCommentResponse(true);
        }
        return new EditCommentResponse(false);
    }

    //TODO: 예외 처리 함수 [규민]
    @Transactional
    public DeleteCommentResponse commentDelete(DeleteCommentRequest comment,
                                               @LoginMember LoggedInMember loggedInMember) {
        Comment savedComment = commentRepository.findById(comment.getId())
            .orElseThrow(() -> new ClassmoaException(ClassmoaErrorCode.NOT_FOUND_COMMENT));
        Member member = memberService.findMemberById(loggedInMember.getMemberId());

        if(savedComment.getMember().equals(member)){
            savedComment.deleteComment(true);
            return new DeleteCommentResponse(true);
        }
        return new DeleteCommentResponse(false);
    }
}
