package com.dev.classmoa.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.dev.classmoa.domain.entity.*;
import com.dev.classmoa.domain.repository.CommentRepository;
import com.dev.classmoa.domain.repository.LectureRepository;
import com.dev.classmoa.domain.repository.MemberRepository;
import com.dev.classmoa.domain.repository.OpinionRepository;
import com.dev.classmoa.dto.comment.request.CreateCommentRequest;
import com.dev.classmoa.dto.comment.request.DeleteCommentRequest;
import com.dev.classmoa.dto.comment.request.EditCommentRequest;
import com.dev.classmoa.dto.comment.response.EditCommentResponse;
import com.dev.classmoa.dto.opinion.request.CreateOpinionRequest;
import com.dev.classmoa.dto.opinion.request.DeleteOpinionRequest;
import com.dev.classmoa.dto.opinion.request.EditOpinionRequest;
import com.dev.classmoa.dto.opinion.response.EditOpinionResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

	@Autowired
	LectureRepository lectureRepository;

	@AfterEach
	public void init(){

	}

	@BeforeEach
	void CreateTestData() {
		Lecture lecture1 = Lecture.builder()
				.companyName("카클스")
				.imageLink("어딘가 있음")
				.lectureId("카클스1")
				.instructor("임창준")
				.ordinaryPrice(100000)
				.salePercent("30%")
				.salePrice(70000)
				.siteLink("www.ybm.com")
				.title("오사카 여행 가이드")
				.build();

		Lecture lecture2 = Lecture.builder()
				.lectureId("카클스2")
				.companyName("카클스")
				.imageLink("어딘가 있음")
				.instructor("임창준")
				.ordinaryPrice(100000)
				.salePercent("30%")
				.salePrice(70000)
				.siteLink("www.ybm.com")
				.title("오사카 여행 가이드")
				.build();

		Lecture lecture3 = Lecture.builder()
				.lectureId("카클스3")
				.companyName("카클스")
				.imageLink("임창준 아이폰")
				.instructor("임창준")
				.ordinaryPrice(100000)
				.salePercent("30%")
				.salePrice(70000)
				.siteLink("www.창준교토.gg")
				.title("교토 여행 가이드")
				.build();

		lectureRepository.save(lecture1);
		lectureRepository.save(lecture2);
		lectureRepository.save(lecture3);


		Member member = Member.signup()
				.memberName("123@gmail.com")
				.password("123")
				.nickname("동그리")
				.signupbuild();
		memberRepository.save(member);

		Opinion opinion1 = Opinion.builder()
				.content("의견1")
				.member(member)
				.lecture(lecture1)
				.build();

		Opinion opinion2 = Opinion.builder()
				.content("의견2")
				.member(member)
				.lecture(lecture1)
				.build();

		opinionRepository.save(opinion1);
		opinionRepository.save(opinion2);

		Comment comment1 = Comment.builder()
				.content("댓글1")
				.member(member)
				.opinion(opinion1)
				.build();

		Comment comment2 = Comment.builder()
				.content("댓글2")
				.member(member)
				.opinion(opinion1)
				.build();

		commentRepository.save(comment1);
		commentRepository.save(comment2);
	}

	@Test
	@DisplayName("lectureId와 회원 정보를 받아서 의견을 생성 한다.")
	@Rollback(value = false)
	void createOpinion(){
		//given
		String lectureId = "카클스3";
		Member member = memberRepository.findById(1L).get();
		CreateOpinionRequest createOpinion = CreateOpinionRequest.builder()
				.content("생성 의견")
				.build();

		//when
		opinionService.createOpinion(createOpinion, lectureId, member);

		//then
		assertThat(opinionRepository.findById(3L).get().getMember())
				.isEqualTo(member);
	}

	@Test
	@DisplayName("lectureId를 받아서 의견을 조회 한다.")
	void getOpinions() {
		//given
		String lectureId = "카클스1";

		//when
		List<Opinion> opinionList = opinionService.getOpinions(lectureId);

		//then
		assertThat(opinionList.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("의견 정보와 회원 정보를 받아서 의견을 수정 한다.")
	void editOpinion() {
		//given
		Member member = memberRepository.findById(1L).get();
		EditOpinionRequest editOpinion = EditOpinionRequest.builder()
				.id(1L)
				.content("수정 의견")
				.build();
		//when
		EditOpinionResponse response = opinionService.editOpinion(editOpinion, member);
		Opinion editedOpinion = opinionRepository.findById(1L).get();

		//then
		assertThat(response.getIsModified()).isTrue();
		assertThat(editedOpinion.getContent()).isEqualTo("수정 의견");
	}

	@Test
	@DisplayName("의견 정보와 회원 정보를 받아서 의견을 삭제 한다.")
	void deleteOpinion(){
		//given
		Member member = memberRepository.findById(1L).get();
		Opinion opinion = opinionRepository.findById(1L).get();

		//when
		opinionService.deleteOpinion(OpinionEntityToDTO(opinion), member);
		Opinion deletedOpinion = opinionRepository.findById(1L).get();

		//then
		assertThat(deletedOpinion.getIsDeleted()).isTrue();
	}

	@Test
	@DisplayName("opinionId와 회원 정보를 받아서 댓글을 생성 한다.")
	@Rollback(value = false)
	void createComment(){
		//given
		Long opinionId = 1L;
		Member member = memberRepository.findById(1L).get();
		CreateCommentRequest createComment = CreateCommentRequest.builder()
				.content("생성 댓글")
				.opinionId(opinionId)
				.build();

		//when
		opinionService.commentCreate(createComment, opinionId, member);

		//then
		assertThat(commentRepository.findById(3L).get().getMember())
				.isEqualTo(member);
	}

	@Test
	@DisplayName("댓글정 보와 회원 정보를 받아서 댓글을 수정 한다.")
	void editComment() {
		//given
		Member member = memberRepository.findById(1L).get();
		EditCommentRequest newComment = EditCommentRequest.builder()
				.id(1L)
				.content("수정 댓글")
				.build();

		//when
		EditCommentResponse response = opinionService.commentEdit(newComment, member);
		Comment editedComment = commentRepository.findById(1L).get();

		//then
		assertThat(response.getIsModified()).isTrue();
		assertThat(editedComment.getContent()).isEqualTo("수정 댓글");
	}

	@Test
	@DisplayName("댓글 정보와 회원 정보를 받아서 댓글을 삭제 한다.")
	void deleteComment(){
		//given
		Comment comment = commentRepository.findById(1L).get();
		Member member = memberRepository.findById(1L).get();

		//when
		opinionService.commentDelete(CommentEntityToDTO(comment), member);
		Comment deletedComment = commentRepository.findById(1L).get();

		//then
		assertThat(deletedComment.getIsDeleted()).isTrue();
	}

	// 엔티티틀 DTO로 변경하는 메소드

	public DeleteOpinionRequest OpinionEntityToDTO(Opinion opinion){

		DeleteOpinionRequest deleteOpinionRequest = new DeleteOpinionRequest();
		deleteOpinionRequest.setId(opinion.getId());

		return deleteOpinionRequest;
	}

	public DeleteCommentRequest CommentEntityToDTO(Comment comment){

		DeleteCommentRequest deleteCommentRequest = new DeleteCommentRequest();
		deleteCommentRequest.setId(comment.getId());

		return deleteCommentRequest;
	}
}