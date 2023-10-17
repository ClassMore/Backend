package com.dev.classmoa.dto.opinion.request;

import com.dev.classmoa.domain.entity.Opinion;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOpinionRequest {

    @Size(max = 100)
    @NotNull
    private String content;

}
