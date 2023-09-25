package com.example.classmoa.dto.interest.request;

import com.example.classmoa.domain.entity.InterestLecture;
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
