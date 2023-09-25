package com.example.classmoa.dto.comment.request;

import com.example.classmoa.domain.entity.Comment;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteComment {

    private Long id;

    public Comment toEntity() {
        return Comment.deleter()
                .id(id)
                .delete();
    }
}
