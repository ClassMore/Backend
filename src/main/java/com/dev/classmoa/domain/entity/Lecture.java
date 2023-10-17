package com.dev.classmoa.domain.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Lecture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "lecture_id")
	private String lectureId;
	private String title;
	private String instructor;
	private String companyName;
	private int ordinaryPrice;
	private int salePrice;
	private String salePercent;

	@CreatedDate
	private LocalDate date;
	private String siteLink;
	private String imageLink;

	@OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
	private List<LectureTag> lectureTags = new ArrayList<>();

	@OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
	private List<Opinion> opinions = new ArrayList<>();

	@Builder
	public Lecture(String lectureId, String title, String instructor, String companyName, int ordinaryPrice,
		int salePrice,
		String salePercent, String siteLink, String imageLink) {
		this.lectureId = lectureId;
		this.title = title;
		this.instructor = instructor;
		this.companyName = companyName;
		this.ordinaryPrice = ordinaryPrice;
		this.salePrice = salePrice;
		this.salePercent = salePercent;
		this.siteLink = siteLink;
		this.imageLink = imageLink;
	}

	//연관관계 메서드 작성
	// public void setLectureTags(LectureTag lectureTag){
	//     this.getLectureTags().add(lectureTag);
	//     lectureTag.setLectures(this);
	// }
}
