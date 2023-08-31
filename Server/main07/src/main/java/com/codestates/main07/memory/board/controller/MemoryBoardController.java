package com.codestates.main07.memory.board.controller;

import com.codestates.main07.memory.board.dto.*;
import com.codestates.main07.memory.board.entity.MemoryBoard;
import com.codestates.main07.memory.board.mapper.MemoryBoardMapper;
import com.codestates.main07.memory.board.service.MemoryBoardService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/memoryBoards")
public class MemoryBoardController {
    private final MemoryBoardService service;
    private final MemoryBoardMapper mapper;

    public MemoryBoardController(MemoryBoardService service, MemoryBoardMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity createMemoryBoard(@RequestBody MemoryBoardCreateDto createDto) {

        MemoryBoard memoryBoard = mapper.createDtoToMemoryBoard(createDto);
        service.createMemoryBoard(memoryBoard);
        MemoryBoardResponseDto response = mapper.memoryBoardToResponseDto(memoryBoard);

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{memoryBoard-id}")
    public ResponseEntity updateMemoryBoard(@PathVariable ("memoryBoard-id") long memoryBoardId,
                                            @RequestBody MemoryBoardUpdateDto updateDto) {

        MemoryBoard memoryBoard = mapper.updateDtoToMemoryBoard(updateDto);
        memoryBoard.setMemoryBoardId(memoryBoardId);
        service.updateMemoryBoard(memoryBoard);

        MemoryBoard updatedMemoryBoard = service.findMemoryBoard(memoryBoardId);
        MemoryBoardResponseDto response = mapper.memoryBoardToResponseDto(updatedMemoryBoard);

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{memoryBoard-id}")
    public ResponseEntity getMemoryBoard(@PathVariable ("memoryBoard-id") long memoryBoardId) {

        MemoryBoard memoryBoard = service.findMemoryBoard(memoryBoardId);
        MemoryBoardResponseDto response = mapper.memoryBoardToResponseDto(memoryBoard);

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMemoryBoards(@Positive @RequestParam int page,
                                          @Positive @RequestParam int size) {

        Page<MemoryBoard> pageMemoryBoards = service.findMemoryBoards(page - 1, size);
        List<MemoryBoard> memoryBoards = pageMemoryBoards.getContent();
        List<MemoryBoardResponsesDto> responses = mapper.memoryBoardsToResponsesDto(memoryBoards);

        return new ResponseEntity<>(
                new MultiResponseDto<>(responses, pageMemoryBoards, true), HttpStatus.OK);
    }

    @DeleteMapping("/{memoryBoard-id}")
    public ResponseEntity deleteMemoryBoard(@PathVariable ("memoryBoard-id") long memoryBoardId) {
        service.deleteMemoryBoard(memoryBoardId);
        return new ResponseEntity<>(new SuccessDto(true), HttpStatus.OK);
    }
}
