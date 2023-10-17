package com.dev.classmoa.dto.comment.request;

import com.dev.classmoa.domain.entity.Comment;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCommentRequest {

    @Positive(message = "유효 하지 않은 값 입니다.")
    private Long id;

}
