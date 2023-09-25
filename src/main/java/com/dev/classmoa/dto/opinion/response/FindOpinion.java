package com.dev.classmoa.dto.opinion.response;

import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.entity.Opinion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
