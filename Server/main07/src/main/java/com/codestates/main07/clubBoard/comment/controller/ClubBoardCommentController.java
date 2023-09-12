package com.codestates.main07.clubBoard.comment.controller;

import com.codestates.main07.clubBoard.board.entity.ClubBoard;
import com.codestates.main07.clubBoard.board.service.ClubBoardService;
import com.codestates.main07.clubBoard.comment.dto.*;
import com.codestates.main07.clubBoard.comment.entity.ClubBoardComment;
import com.codestates.main07.clubBoard.comment.mapper.ClubBoardCommentMapper;
import com.codestates.main07.clubBoard.comment.service.ClubBoardCommentService;
import com.codestates.main07.clubBoard.response.SuccessDto;
import com.codestates.main07.jwt.auth.jwt.JwtTokenizer;
import com.codestates.main07.member.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubBoards")
@CrossOrigin
public class ClubBoardCommentController {
    private final ClubBoardCommentService service;
    private final ClubBoardService boardService;
    private final ClubBoardCommentMapper mapper;
    private final MemberService memberService;

    public ClubBoardCommentController(ClubBoardCommentService service, ClubBoardService boardService, ClubBoardCommentMapper mapper, MemberService memberService) {
        this.service = service;
        this.boardService = boardService;
        this.mapper = mapper;
        this.memberService = memberService;
    }

    @PostMapping("/{clubBoards-id}/comments")
    public ResponseEntity createComment(@PathVariable ("clubBoards-id") long clubBoardId,
                                        @RequestBody ClubBoardCommentCreateDto createDto) {

        ClubBoardComment comment = mapper.createDtoToComment(createDto);
        comment.setClubBoard(boardService.findClubBoard(clubBoardId));
        comment.setMember(memberService.viewMember(createDto.getMemberId()));

        ClubBoardComment createdComment = service.createComment(comment);
        ClubBoardCommentResponseDto response = mapper.commentToResponseDTo(createdComment);

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{clubBoards-id}/comments/{clubBoardComment-id}")
    public ResponseEntity updateComment(@PathVariable ("clubBoards-id") long clubBoardId,
                                        @PathVariable ("clubBoardComment-id") long clubBoardCommentId,
                                        @RequestBody ClubBoardCommentUpdateDto updateDto) {
        ClubBoardComment comment = mapper.updateDtoToComment(updateDto);
        comment.setClubBoardCommentId(clubBoardCommentId);
        service.updateComment(comment);

        ClubBoardComment updatedComment = service.findComment(clubBoardCommentId);
        ClubBoardCommentResponseDto response = mapper.commentToResponseDTo(updatedComment);

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{clubBoards-id}/comments/{clubBoardComment-id}")
    public ResponseEntity viewComment(@PathVariable ("clubBoards-id") long clubBoardId,
                                      @PathVariable ("clubBoardComment-id") long clubBoardCommentId) {
        ClubBoardComment comment = service.findComment(clubBoardCommentId);
        ClubBoardCommentResponseDto response = mapper.commentToResponseDTo(comment);

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{clubBoards-id}/comments")
    public ResponseEntity viewComments(@PathVariable ("clubBoards-id") long clubBoardId,
                                       @RequestParam int page,
                                       @RequestParam int size) {

        Page<ClubBoardComment> pageComments = service.findComments(page - 1, size, clubBoardId);
        List<ClubBoardComment> comments = pageComments.getContent();
        List<ClubBoardCommentResponsesDto> responses = mapper.commentsToResponsesDto(comments);

        return new ResponseEntity<>(
                new ClubBoardCommentMultiResponseDto<>(
                        responses, pageComments, true), HttpStatus.OK
        );
    }

    @DeleteMapping("/{clubBoards-id}/comments/{clubBoardComment-id}")
    public ResponseEntity deleteComment(@PathVariable ("clubBoards-id") long clubBoardId,
                                        @PathVariable ("clubBoardComment-id") long clubBoardCommentId) {
        service.deleteComment(clubBoardCommentId);
        return new ResponseEntity<>(new SuccessDto(true), HttpStatus.OK);
    }
}
