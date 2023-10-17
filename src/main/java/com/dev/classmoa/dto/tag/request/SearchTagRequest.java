package com.dev.classmoa.dto.tag.request;

import com.dev.classmoa.domain.entity.Tag;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchTagRequest {

    @Positive(message = "유효 하지 않은 값 입니다.")
    private Long id;

}
