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
public class EditComment {

    private Boolean isModified;

    public EditComment(Comment comment) {
        this.isModified = comment.getIsModified();
    }
}
