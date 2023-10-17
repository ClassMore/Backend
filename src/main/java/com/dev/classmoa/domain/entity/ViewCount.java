package com.dev.classmoa.domain.entity;

import static jakarta.persistence.FetchType.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ViewCount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "lecture_id", unique = true)
	private Lecture lecture;

	private int viewCount;

	@Builder
	public ViewCount(Lecture lecture, int viewCount) {
		this.lecture = lecture;
		this.viewCount = viewCount;
	}

	public void viewCountUp() {
		this.viewCount = this.viewCount + 1;
	}
}
