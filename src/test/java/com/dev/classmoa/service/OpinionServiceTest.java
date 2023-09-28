package com.dev.classmoa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.dev.classmoa.domain.entity.Comment;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.entity.Opinion;
import com.dev.classmoa.domain.repository.CommentRepository;
import com.dev.classmoa.domain.repository.MemberRepository;
import com.dev.classmoa.domain.repository.OpinionRepository;
import com.dev.classmoa.dto.comment.request.CreateCommentRequest;
import com.dev.classmoa.dto.comment.request.EditCommentRequest;
import com.dev.classmoa.dto.comment.response.EditCommentResponse;
import com.dev.classmoa.dto.opinion.request.CreateOpinionRequest;
import com.dev.classmoa.dto.opinion.request.EditOpinionRequest;
import com.dev.classmoa.dto.opinion.response.CreateOpinionResponse;
import com.dev.classmoa.dto.opinion.response.DeleteOpinionResponse;
import com.dev.classmoa.dto.opinion.response.EditOpinionResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.repository.LectureRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class OpinionServiceTest {

	@Autowired
	OpinionService opinionService;

	@Autowired
	OpinionRepository opinionRepository;

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	MemberRepository memberRepository;

	@Test
	@DisplayName("의견을 생성한다.")
	@Rollback(value = false)
	void createOpinion(){
		//given
		String lectureId = "artstudyjwLee508";
		Member member = memberRepository.findById(1L).get();
		CreateOpinionRequest newOpinion = CreateOpinionRequest.builder()
				.content("ㅎㅎ2")
				.build();

		//when
		Long resultId = opinionService.create(newOpinion.toEntity(),lectureId, member).getId();

		//then
		assertThat(opinionRepository.findById(resultId).get().getMember())
				.isEqualTo(member);
	}

	@Test
	@DisplayName("의견들을 조회한다")
	void getOpinions() {
		//given
		String lectureId = "artstudyjwLee508";

		//when
		List<Opinion> opinionList = opinionService.getOpinions(lectureId);

		//then
		assertThat(opinionList.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("의견을 수정한다")
	void editOpinion() {
		//given
		Member member = memberRepository.findById(1L).get();
		EditOpinionRequest editOpinion = EditOpinionRequest.builder()
				.id(1L)
				.content("ㅎㅎㅎㅎㅎ")
				.build();
		//when
		EditOpinionResponse response = opinionService.edit(editOpinion.toEntity(), member);
		Opinion editedOpinion = opinionRepository.findById(1L).get();

		//then
		assertThat(response.getIsModified()).isTrue();
		assertThat(editedOpinion.getContent()).isEqualTo("ㅎㅎㅎㅎㅎ");
	}

	@Test
	@DisplayName("의견을 삭제한다.")
	void deleteOpinion(){
		//given
		Opinion opinion = opinionRepository.findById(1L).get();
		Member member = memberRepository.findById(1L).get();

		//when
		opinionService.delete(opinion, member);
		Opinion deletedOpinion = opinionRepository.findById(1L).get();

		//then
		assertThat(deletedOpinion.getIsDeleted()).isTrue();
	}

	@Test
	@DisplayName("댓글을 생성한다.")
	@Rollback(value = false)
	void createComment(){
		//given
		Long opinionId = 1L;
		Member member = memberRepository.findById(1L).get();
		CreateCommentRequest newComment = CreateCommentRequest.builder()
				.content("ㅎㅎ3")
				.build();

		//when
		Long resultId = opinionService.commentCreate(newComment.toEntity(),opinionId, member).getId();

		//then
		assertThat(opinionRepository.findById(resultId).get().getMember())
				.isEqualTo(member);
	}

	@Test
	@DisplayName("댓글을 수정한다")
	void editComment() {
		//given
		Member member = memberRepository.findById(1L).get();
		EditCommentRequest newComment = EditCommentRequest.builder()
				.id(1L)
				.content("ㅎㅎㅎㅎㅎ")
				.build();
		//when
		EditCommentResponse response = opinionService.commentEdit(newComment.toEntity(), member);
		Comment editedComment = commentRepository.findById(1L).get();

		//then
		assertThat(response.getIsModified()).isTrue();
		assertThat(editedComment.getContent()).isEqualTo("ㅎㅎㅎㅎㅎ");
	}

	@Test
	@DisplayName("댓글을 삭제한다.")
	void deleteComment(){
		//given
		Comment comment = commentRepository.findById(1L).get();
		Member member = memberRepository.findById(1L).get();

		//when
		opinionService.commentDelete(comment, member);
		Comment deletedComment = commentRepository.findById(1L).get();

		//then
		assertThat(deletedComment.getIsDeleted()).isTrue();
	}

}