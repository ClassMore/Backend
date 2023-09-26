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

    // 댓글 생성
    public Long commentCreate(Comment newComment, Member member){
        Opinion opinion = opinionRepository.findById(newComment.getOpinion().getId())
            .orElseThrow(() -> new IllegalArgumentException("sss"));
        return commentRepository.save(
            Comment.builder()
                .opinion(opinion)
                .member(member)
                .build()
        ).getId();
    }
    public Boolean commentEdit(Comment newComment){
        return commentRepository.save(newComment).getIsModified();
    }

    public void commentDelete(Comment comment){
        commentRepository.deleteById(comment.getId());
    }

}
