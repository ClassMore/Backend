package com.dev.classmoa.dto.comment.request;

import com.dev.classmoa.domain.entity.Comment;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCommentRequest {

    private Long id;

    public Comment toEntity() {
        return Comment.deleter()
                .id(id)
                .delete();
    }
}
