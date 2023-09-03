package com.codestates.main07.memoryBoard.answer.controller;

import com.codestates.main07.memoryBoard.answer.dto.*;
import com.codestates.main07.memoryBoard.answer.entity.MemoryBoardAnswer;
import com.codestates.main07.memoryBoard.answer.mapper.MemoryBoardAnswerMapper;
import com.codestates.main07.memoryBoard.answer.service.MemoryBoardAnswerService;
import com.codestates.main07.memoryBoard.response.SuccessDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memoryBoards")
@CrossOrigin
public class MemoryBoardAnswerController {
    private final MemoryBoardAnswerService service;
    private final MemoryBoardAnswerMapper mapper;

    public MemoryBoardAnswerController(MemoryBoardAnswerService service, MemoryBoardAnswerMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/{memoryBoard-id}/answers")
    public ResponseEntity createAnswer(@PathVariable("memoryBoard-id") long memoryBoardId,
                                       @RequestBody MemoryBoardAnswerCreateDto createDto) {

        MemoryBoardAnswer answer = mapper.createDtoToAnswer(createDto);
        answer.setMemoryBoardId(memoryBoardId);
        MemoryBoardAnswer savedAnswer = service.createAnswer(answer);

        MemoryBoardAnswerResponseDto response = mapper.answerToResponseDto(savedAnswer);
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{memoryBoard-id}/answers/{memoryBoardAnswer-id}")
    public ResponseEntity updateAnswer(@PathVariable ("memoryBoard-id") long memoryBoardId,
                                       @PathVariable ("memoryBoardAnswer-id") long memoryBoardAnswerId,
                                       @RequestBody MemoryBoardAnswerUpdateDto updateDto) {

        MemoryBoardAnswer updateAnswer = mapper.updateDtoToAnswer(updateDto);
        updateAnswer.setMemoryBoardAnswerId(memoryBoardAnswerId);
        MemoryBoardAnswer newAnswer = service.updateAnswer(updateAnswer);

        MemoryBoardAnswerResponseDto response = mapper.answerToResponseDto(newAnswer);
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{memoryBoard-id}/answers/{memoryBoardAnswer-id}")
    public ResponseEntity viewAnswer(@PathVariable ("memoryBoard-id") long memoryBoardId,
                                     @PathVariable ("memoryBoardAnswer-id") long memoryBoardAnswerId) {

        MemoryBoardAnswer answer = service.findAnswer(memoryBoardAnswerId);
        MemoryBoardAnswerResponseDto response = mapper.answerToResponseDto(answer);
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{memoryBoard-id}/answers")
    public ResponseEntity viewAnswers(@PathVariable ("memoryBoard-id") long memoryBoardId) {

        List<MemoryBoardAnswer> answers = service.findAnswers(memoryBoardId);
        List<MemoryBoardAnswerResponsesDto> responses = mapper.answersToResponsesDto(answers);

        return new ResponseEntity<>(
                new MemoryBoardAnswerMultiResponseDto<>(responses, true), HttpStatus.OK);
    }

    @DeleteMapping("/{memoryBoard-id}/answers/{memoryBoardAnswer-id}")
    public ResponseEntity deleteAnswer(@PathVariable ("memoryBoard-id") long memoryBoardId,
                                       @PathVariable ("memoryBoardAnswer-id") long memoryBoardAnswerId) {

        service.deleteAnswer(memoryBoardAnswerId);

        return new ResponseEntity<>(new SuccessDto(true), HttpStatus.OK);
    }
}
