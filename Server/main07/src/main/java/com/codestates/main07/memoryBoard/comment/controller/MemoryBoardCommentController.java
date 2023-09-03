package com.codestates.main07.memoryBoard.comment.controller;

import com.codestates.main07.memoryBoard.comment.dto.*;
import com.codestates.main07.memoryBoard.comment.entity.MemoryBoardComment;
import com.codestates.main07.memoryBoard.comment.mapper.MemoryBoardCommentMapper;
import com.codestates.main07.memoryBoard.comment.service.MemoryBoardCommentService;
import com.codestates.main07.memoryBoard.response.SuccessDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
@CrossOrigin
public class MemoryBoardCommentController {
    private final MemoryBoardCommentService service;
    private final MemoryBoardCommentMapper mapper;

    public MemoryBoardCommentController(MemoryBoardCommentService service, MemoryBoardCommentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/{memoryBoardAnswer-id}/comments")
    public ResponseEntity createComment(@PathVariable ("memoryBoardAnswer-id") long memoryBoardAnswerId,
                                        @RequestBody MemoryBoardCommentCreateDto createDto) {

        MemoryBoardComment comment = mapper.createDtoToComment(createDto);
        MemoryBoardComment createdComment = service.createComment(comment);
        MemoryBoardCommentResponseDto response = mapper.commentToResponseDTo(createdComment);

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{memoryBoardAnswer-id}/comments/{memoryBoardComment-id}")
    public ResponseEntity updateComment(@PathVariable ("memoryBoardAnswer-id") long memoryBoardAnswerId,
                                        @PathVariable ("memoryBoardComment-id") long memoryBoardCommentId,
                                        @RequestBody MemoryBoardCommentUpdateDto updateDto) {
        MemoryBoardComment comment = mapper.updateDtoToComment(updateDto);
        comment.setMemoryBoardCommentId(memoryBoardCommentId);
        service.updateComment(comment);

        MemoryBoardComment updatedComment = service.findComment(memoryBoardCommentId);
        MemoryBoardCommentResponseDto response = mapper.commentToResponseDTo(updatedComment);

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{memoryBoardAnswer-id}/comments/{memoryBoardComment-id}")
    public ResponseEntity viewComment(@PathVariable ("memoryBoardAnswer-id") long memoryBoardAnswerId,
                                      @PathVariable ("memoryBoardComment-id") long memoryBoardCommentId) {
        MemoryBoardComment comment = service.findComment(memoryBoardCommentId);
        MemoryBoardCommentResponseDto response = mapper.commentToResponseDTo(comment);

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{memoryBoardAnswer-id}/comments")
    public ResponseEntity viewComments(@PathVariable ("memoryBoardAnswer-id") long memoryBoardAnswerId,
                                       @RequestParam int page,
                                       @RequestParam int size) {

        Page<MemoryBoardComment> pageComments = service.findComments(page, size);
        List<MemoryBoardComment> comments = pageComments.getContent();
        List<MemoryBoardCommentResponsesDto> responses = mapper.commentsToResponsesDto(comments);

        return new ResponseEntity<>(
                new MemoryBoardCommentMultiResponseDto<>(
                        responses, pageComments, true), HttpStatus.OK
        );
    }

    @DeleteMapping("/{memoryBoardAnswer-id}/comments/{memoryBoardComment-id}")
    public ResponseEntity deleteComment(@PathVariable ("memoryBoardAnswer-id") long memoryBoardAnswerId,
                                        @PathVariable ("memoryBoardComment-id") long memoryBoardCommentId) {
        service.deleteComment(memoryBoardCommentId);
        return new ResponseEntity<>(new SuccessDto(true), HttpStatus.OK);
    }
}
