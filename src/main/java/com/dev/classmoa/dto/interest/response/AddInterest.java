package com.dev.classmoa.dto.interest.response;

import com.dev.classmoa.domain.entity.InterestLecture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddInterest {

    private Long id;

    public AddInterest (InterestLecture interestLecture) {
        this.id = interestLecture.getId();
    }
}
