package com.codestates.main07.marketBoard.comment;

import com.codestates.main07.marketBoard.board.MarketBoard;
import com.codestates.main07.marketBoard.board.dto.SuccessDto;
import com.codestates.main07.marketBoard.comment.dto.MarketBoardCommentCreate;
import com.codestates.main07.marketBoard.comment.dto.MarketBoardCommentResponse;
import com.codestates.main07.marketBoard.comment.dto.MarketBoardCommentUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping("/marketBoardComments")
@RestController
public class MarketBoardCommentController {
    private final MarketBoardCommentService marketBoardCommentService;
    private final MarketBoardCommentMapper mapper;

    @GetMapping
    public ResponseEntity marketBoardCommentList (@Positive @RequestParam int page,
                                                  @Positive @RequestParam int size) {
        Page<MarketBoardComment> marketBoardCommentPage = marketBoardCommentService.commentList(page -1, size);
        List<MarketBoardComment> marketBoardComments = marketBoardCommentPage.getContent();

        List<MarketBoardCommentResponse> response =
                marketBoardComments.stream()
                        .map(mapper::marketBoardCommentToMarketBoardCommentResponseDto)
                        .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{marketBoardCommentId}")
    public ResponseEntity viewComment (@PathVariable ("marketBoardCommentId") long marketBoardCommentId) {

        MarketBoardComment marketBoardComment = marketBoardCommentService.viewComment(marketBoardCommentId);
        MarketBoardCommentResponse response = mapper.marketBoardCommentToMarketBoardCommentResponseDto(marketBoardComment);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addComment (@RequestBody MarketBoardCommentCreate createDto) {

        MarketBoardComment marketBoardComment = mapper.createDtoToMarketBoardComment(createDto);

        MarketBoardComment createdMarketBoardComment = marketBoardCommentService.createComment(marketBoardComment);
        MarketBoardCommentResponse response = mapper.marketBoardCommentToMarketBoardCommentResponseDto(createdMarketBoardComment);

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{marketBoardCommentId}")
    public ResponseEntity editComment (@PathVariable ("marketBoardCommentId") long marketBoardCommentId,
                                       @RequestBody MarketBoardCommentUpdate updateDto) {

        updateDto.setMarketBoardCommentId(marketBoardCommentId);
        MarketBoardComment marketBoardComment = mapper.updateDtoToMarketBoardComment(updateDto);

        MarketBoardComment updatedMarketBoardComment = marketBoardCommentService.updateComment(marketBoardComment);
        MarketBoardCommentResponse response = mapper.marketBoardCommentToMarketBoardCommentResponseDto(updatedMarketBoardComment);

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{marketBoardCommentId}")
    public ResponseEntity deleteComment (@PathVariable ("marketBoardCommentId") long marketBoardCommentId) {

        marketBoardCommentService.deleteComment(marketBoardCommentId);

        return new ResponseEntity<>(new SuccessDto(true), HttpStatus.OK);
    }
}
