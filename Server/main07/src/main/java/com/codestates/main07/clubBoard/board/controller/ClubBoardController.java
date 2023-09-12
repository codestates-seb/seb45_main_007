package com.codestates.main07.clubBoard.board.controller;

import com.codestates.main07.clubBoard.board.dto.*;
import com.codestates.main07.clubBoard.board.entity.ClubBoard;
import com.codestates.main07.clubBoard.board.mapper.ClubBoardMapper;
import com.codestates.main07.clubBoard.board.service.ClubBoardService;
import com.codestates.main07.clubBoard.response.SuccessDto;
import com.codestates.main07.jwt.auth.filter.JwtAuthenticationFilter;
import com.codestates.main07.jwt.auth.jwt.JwtTokenizer;
import com.codestates.main07.member.entity.Member;
import com.codestates.main07.member.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/clubBoards")
@CrossOrigin
public class ClubBoardController {
    private final ClubBoardService service;
    private final ClubBoardMapper mapper;
    private final MemberService memberService;

    public ClubBoardController(ClubBoardService service, ClubBoardMapper mapper, MemberService memberService) {
        this.service = service;
        this.mapper = mapper;
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity createClubBoard(@RequestBody ClubBoardCreateDto createDto) {

        ClubBoard clubBoard = mapper.createDtoToClubBoard(createDto);
        clubBoard.setMember(memberService.viewMember(clubBoard.getMember().getMemberId()));

        ClubBoard createdClubBoard = service.createClubBoard(clubBoard);
        ClubBoardResponseDto response = mapper.clubBoardToResponseDto(createdClubBoard);

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{clubBoard-id}")
    public ResponseEntity updateClubBoard(@PathVariable ("clubBoard-id") long clubBoardId,
                                          @RequestBody ClubBoardUpdateDto updateDto) {

        ClubBoard clubBoard = mapper.updateDtoToClubBoard(updateDto);
        clubBoard.setClubBoardId(clubBoardId);
        service.updateClubBoard(clubBoard);

        ClubBoard updatedClubBoard = service.findClubBoard(clubBoardId);
        ClubBoardResponseDto response = mapper.clubBoardToResponseDto(updatedClubBoard);

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{clubBoard-id}")
    public ResponseEntity viewClubBoard(@PathVariable ("clubBoard-id") long clubBoardId) {

        ClubBoard clubBoard = service.findClubBoard(clubBoardId);
        service.plusViewCount(clubBoard);
        ClubBoardResponseDto response = mapper.clubBoardToResponseDto(clubBoard);

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity viewClubBoards(@Positive @RequestParam int page,
                                          @Positive @RequestParam int size) {

        Page<ClubBoard> pageClubBoards = service.findClubBoards(page - 1, size);
        List<ClubBoard> clubBoards = pageClubBoards.getContent();
        List<ClubBoardResponsesDto> responses = mapper.clubBoardsToResponsesDto(clubBoards);

        return new ResponseEntity<>(
                new ClubBoardMultiResponseDto<>(responses, pageClubBoards, true), HttpStatus.OK
        );
    }

    @DeleteMapping("/{clubBoard-id}")
    public ResponseEntity deleteClubBoard(@PathVariable ("clubBoard-id") long clubBoardId) {
        service.deleteClubBoard(clubBoardId);
        return new ResponseEntity<>(new SuccessDto(true), HttpStatus.OK);
    }

    // 검색 기능
    @GetMapping("/searches")
    public ResponseEntity searchClubBoardsByTitle(@Positive @RequestParam int page,
                                                  @Positive @RequestParam int size,
                                                  @Positive @RequestParam String keyword) {

        Page<ClubBoard> pageClubBoards = service.searchClubBoardsByTitle(page - 1, size, keyword);
        List<ClubBoard> clubBoards = pageClubBoards.getContent();
        List<ClubBoardResponsesDto> responses = mapper.clubBoardsToResponsesDto(clubBoards);

        return new ResponseEntity<>(
                new ClubBoardMultiResponseDto<>(responses, pageClubBoards, true), HttpStatus.OK);
    }
}
