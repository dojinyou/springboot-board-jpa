package com.dojinyou.devcourse.boardjpa.post.controller;

import com.dojinyou.devcourse.boardjpa.post.controller.dto.PostCreateRequest;
import com.dojinyou.devcourse.boardjpa.post.controller.dto.PostUpdateRequest;
import com.dojinyou.devcourse.boardjpa.post.service.PostService;
import com.dojinyou.devcourse.boardjpa.post.service.dto.PostCreateDto;
import com.dojinyou.devcourse.boardjpa.post.service.dto.PostResponseDto;
import com.dojinyou.devcourse.boardjpa.post.service.dto.PostUpdateDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class PostApiController {
    private final PostService postService;

    public PostApiController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<PostResponseDto> findAll(
            @PageableDefault(sort = {"createdAt"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return postService.findAll(pageable);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody @Valid PostCreateRequest postCreateRequest) {
        long createUserId = postCreateRequest.getUserId();
        PostCreateDto postCreateDto = new PostCreateDto.Builder().title(postCreateRequest.getTitle())
                                                                 .content(postCreateRequest.getContent())
                                                                 .build();
        postService.create(createUserId, postCreateDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    PostResponseDto findById(@PathVariable("id") long id) {
        return postService.findById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateById(@PathVariable("id") long id, @RequestBody @Valid PostUpdateRequest postUpdateRequest) {
        postService.updateById(id, PostUpdateDto.from(postUpdateRequest));
    }
}
