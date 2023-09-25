package com.dev.classmoa.controller;

import com.dev.classmoa.dto.tag.response.FindTag;
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

    @GetMapping("/")
    public ResponseEntity<List<FindTag>> getTags(Pageable pageable) {
        List<FindTag> tags = tagService.getTagList(pageable)
                .stream().map(FindTag::new).toList();
        return ResponseEntity.ok(tags);
    }
}
