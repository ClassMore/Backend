package com.example.classmoa.dto.comment.response;

import com.example.classmoa.domain.entity.Comment;
import com.example.classmoa.domain.entity.Opinion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateComment {

    private Long id;

    public CreateComment(Comment comment) {
        this.id = comment.getId();
    }
}
