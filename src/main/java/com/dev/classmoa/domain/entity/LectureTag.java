package com.dev.classmoa.domain.entity;

import static jakarta.persistence.FetchType.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class LectureTag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lectag_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "lecture_id", referencedColumnName = "lecture_id")
	private Lecture lecture;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "tag_id")
	@Setter
	private Tag tag;

	@Builder
	public LectureTag(Lecture lecture, Tag tag) {
		this.lecture = lecture;
		this.tag = tag;
	}

	//연관관계 메서드 작성

	// public void setLectures(Lecture lecture){
	//     this.lecture = lecture;
	//     lecture.getLectureTags().add(this);
	// }
	//
	// public void setTags(Tag tag){
	//     this.tag = tag;
	//     tag.getLectureTags().add(this);
	// }
}
