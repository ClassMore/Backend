package com.dev.classmoa.dto.comment.request;

import com.dev.classmoa.domain.entity.Comment;
import com.dev.classmoa.domain.entity.Opinion;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {

    @Size(max = 100, message = "100자 이하로 작성해 주세요.")
    @NotNull(message = "댓글 내용을 입력해 주세요.")
    private String content;

    @Positive(message = "유효 하지 않은 값 입니다.")
    private Long opinionId;

}
