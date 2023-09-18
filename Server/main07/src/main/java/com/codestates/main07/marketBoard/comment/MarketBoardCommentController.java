package com.codestates.main07.marketBoard.comment;

import com.codestates.main07.marketBoard.board.dto.SuccessDto;
import com.codestates.main07.marketBoard.comment.dto.MarketBoardCommentCreate;
import com.codestates.main07.marketBoard.comment.dto.MarketBoardCommentResponse;
import com.codestates.main07.marketBoard.comment.dto.MarketBoardCommentUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping("/marketBoards/{marketBoardId}/comments")
@RestController
public class MarketBoardCommentController {
    private final MarketBoardCommentService marketBoardCommentService;
    private final MarketBoardCommentMapper mapper;

    @GetMapping
    public ResponseEntity <List<MarketBoardCommentResponse>> marketBoardCommentList (Pageable pageable,
                                                                                     @PathVariable long marketBoardId) {
        Page<MarketBoardComment> marketBoardCommentPage = marketBoardCommentService.commentList(pageable);
        List<MarketBoardCommentResponse> response = marketBoardCommentPage.getContent()
                .stream()
                .map(mapper::marketBoardCommentToMarketBoardCommentResponseDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{marketBoardCommentId}")
    public ResponseEntity viewComment (@PathVariable long marketBoardId,
                                       @PathVariable ("marketBoardCommentId") long marketBoardCommentId) {

        MarketBoardComment marketBoardComment = marketBoardCommentService.viewComment(marketBoardCommentId);
        MarketBoardCommentResponse response = mapper.marketBoardCommentToMarketBoardCommentResponseDto(marketBoardComment);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createComment(@PathVariable long marketBoardId,
                                        @RequestBody MarketBoardCommentCreate createDto) {

        MarketBoardComment marketBoardComment = mapper.createDtoToMarketBoardComment(createDto);

        MarketBoardComment createdComment = marketBoardCommentService.createComment(marketBoardComment, createDto);
        MarketBoardCommentResponse response = mapper.marketBoardCommentToMarketBoardCommentResponseDto(createdComment);

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{marketBoardCommentId}")
    public ResponseEntity editComment (@PathVariable long marketBoardId,
                                       @PathVariable ("marketBoardCommentId") long marketBoardCommentId,
                                       @RequestBody MarketBoardCommentUpdate updateDto) {

        MarketBoardComment updatedMarketBoardComment = marketBoardCommentService.updateComment(marketBoardCommentId, updateDto);
        MarketBoardCommentResponse response = mapper.marketBoardCommentToMarketBoardCommentResponseDto(updatedMarketBoardComment);

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{marketBoardCommentId}")
    public ResponseEntity deleteComment (@PathVariable long marketBoardId,
                                         @PathVariable ("marketBoardCommentId") long marketBoardCommentId) {

        marketBoardCommentService.deleteComment(marketBoardCommentId);

        return new ResponseEntity<>(new SuccessDto(true), HttpStatus.OK);
    }
}
