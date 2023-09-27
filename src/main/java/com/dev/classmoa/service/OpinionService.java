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

    // Member 객체가 굳이 필요하지 않을거 같음.
    // TODO: 업데이트라면 save 할 필요 없을 것 같은데, Setter 역할을 하는 메소드를 하나 만들자.
    public Boolean edit(Opinion opinion, Member member){
        Opinion savedOpinion = opinionRepository.findById(opinion.getId())
            .orElseThrow(() -> new IllegalArgumentException("not found"));

        if(savedOpinion.getMember().equals(member)){
            return opinionRepository.save(
                savedOpinion.builder()
                        .isModified(true)
                        .content(opinion.getContent())
                        .build()
            ).getIsModified();
        }
        return false;
    }

    // Member 객체가 굳이 필요하지 않을거 같음.
    //TODO: 예외 처리 함수

    public void delete(Opinion opinion, Member member) {
        Opinion savedOpinion = opinionRepository.findById(opinion.getId())
            .orElseThrow(() -> new IllegalArgumentException("not found"));
        if(savedOpinion.getMember().equals(member)) {
            opinionRepository.deleteById(opinion.getId());
        }
    }

    // 댓글 생성
    //TODO: ????
    public Long commentCreate(Comment newComment, Member member){
        return commentRepository.save(
            newComment.builder()
                .opinion(newComment.getOpinion())
                .member(member)
                .build()
        ).getId();
    }
    public Boolean commentEdit(Comment newComment, Member member){
        Comment comment = commentRepository.findById(newComment.getId())
            .orElseThrow(() -> new IllegalArgumentException("not found"));
        if(comment.getMember().equals(member)){
            comment.setContent(newComment.getContent());
        }
        return false;
    }

    //TOOD: 예외 처리 함수
    public void commentDelete(Comment comment, Member member) {
        Comment savedComment = commentRepository.findById(comment.getId())
            .orElseThrow(() -> new IllegalArgumentException("not found"));
        if(savedComment.getMember().equals(member)){
            commentRepository.deleteById(comment.getId());
        }
    }
}
