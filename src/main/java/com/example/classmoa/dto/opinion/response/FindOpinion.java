package com.example.classmoa.dto.opinion.response;

import com.example.classmoa.domain.entity.Member;
import com.example.classmoa.domain.entity.Opinion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindOpinion {

    private Long id;
    private String content;
    private Member member;

    public FindOpinion(Opinion opinion) {
        this.id = opinion.getId();
        this.content = opinion.getContent();
        this.member = opinion.getMember();
    }

}
