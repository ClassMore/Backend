package com.dev.classmoa.dto.comment.request;

import com.dev.classmoa.domain.entity.Comment;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditCommentRequest {

    private Long id;
    private String content;

    public Comment toEntity() {
        return Comment.editer()
                .id(id)
                .content(content)
                .isModified(true)
                .edit();
    }
}
