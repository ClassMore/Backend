package com.dev.classmoa.service;

import com.dev.classmoa.domain.entity.Comment;
import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.entity.Opinion;
import com.dev.classmoa.domain.repository.CommentRepository;
import com.dev.classmoa.domain.repository.OpinionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpinionService {

    private final OpinionRepository opinionRepository;
    private final LectureService lectureService;
    private final CommentRepository commentRepository;

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

    // TODO: 업데이트라면 save 할 필요 없을 것 같은데, Setter 역할을 하는 메소드를 하나 만들자. [창준]
    public Boolean edit(Opinion opinion, Member member){
        Opinion savedOpinion = opinionRepository.findById(opinion.getId())
            .orElseThrow(() -> new IllegalArgumentException("not found"));

        if(savedOpinion.getMember().equals(member)){
            savedOpinion.editOpinion(savedOpinion.getContent());
            return true;
        }
        return false;
    }

    // TODO: 예외 처리 함수
    public Boolean delete(Opinion opinion, Member member) {
        Opinion savedOpinion = opinionRepository.findById(opinion.getId())
            .orElseThrow(() -> new IllegalArgumentException("not found"));
        if(savedOpinion.getMember().equals(member)) {
            savedOpinion.deleteOpinion(true);
            return true;
        }
        return false;
    }

    // 댓글 생성
    //TODO: ???? [가영]
    public Long commentCreate(Comment newComment, Member member){
        return commentRepository.save(
            Comment.builder()
                .content(newComment.getContent())
                .opinion(newComment.getOpinion())
                .member(member)
                .build()
        ).getId();
    }

    public Boolean commentEdit(Comment newComment, Member member){
        Comment comment = commentRepository.findById(newComment.getId())
            .orElseThrow(() -> new IllegalArgumentException("not found"));
        if(comment.getMember().equals(member)){
            comment.editComment(newComment.getContent());
        }
        return false;
    }

    //TODO: 예외 처리 함수 [규민]
    public Boolean commentDelete(Comment comment, Member member) {
        Comment savedComment = commentRepository.findById(comment.getId())
            .orElseThrow(() -> new IllegalArgumentException("not found"));
        if(savedComment.getMember().equals(member)){
            comment.deleteComment(true);
            return true;
        }
        return false;
    }
}
