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

    @Size(max = 100, message = "100자 이하로 작성해 주세요.")
    @NotNull(message = "의견 내용을 입력해 주세요.")
    private String content;

}
