package com.dev.classmoa.controller;

import com.dev.classmoa.dto.tag.response.FindTagResponse;
import com.dev.classmoa.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping("/tag")
    public ResponseEntity<List<FindTagResponse>> getTags(Pageable pageable) {
        List<FindTagResponse> tags = tagService.getTagList(pageable)
                .stream().map(FindTagResponse::new).toList();
        return ResponseEntity.ok(tags);
    }
}
