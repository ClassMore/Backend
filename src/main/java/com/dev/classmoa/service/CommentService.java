package com.dev.classmoa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.classmoa.domain.entity.Comment;
import com.dev.classmoa.domain.entity.Opinion;
import com.dev.classmoa.domain.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final OpinionService opinionService;

	public List<Comment> getCommentList(String lectureId){
		List<Opinion> opinions = opinionService.getOpinions(lectureId);
		for (Opinion opinion : opinions) {
			opinion.getComments();
		}
	}
}
