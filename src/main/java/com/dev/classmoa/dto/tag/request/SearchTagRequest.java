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

    @Positive
    private Long id;

}
