package com.dev.classmoa.dto.interest.request;

import com.dev.classmoa.domain.entity.InterestLecture;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CancelInterest {

    private Long id;

    public InterestLecture toEntity() {
        return InterestLecture.canceler()
                .id(id)
                .cancel();
    }
}
