package com.codestates.main07.marketBoard.board;

import com.codestates.main07.marketBoard.board.dto.MarketBoardResponseDto;
import com.codestates.main07.marketBoard.board.dto.MarketBoardSaveDto;
import com.codestates.main07.marketBoard.board.dto.MarketBoardUpdateDto;
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
@RequestMapping("/marketBoards")
@RestController
public class MarketBoardController {
    private final MarketBoardService marketBoardService;
    private final MarketBoardMapper mapper;

    //게시글 목록 조회
    @GetMapping("/")
    public ResponseEntity selectMarketBoardList(@Positive @RequestParam int page,
                                                @Positive @RequestParam int size) {
        Page<MarketBoard> marketBoardPage = marketBoardService.boardList(page - 1, size);
        List<MarketBoard> marketBoards = marketBoardPage.getContent();

        List<MarketBoardResponseDto> response =
                marketBoards.stream()
                        .map(mapper::marketBoardToMarketBoardResponseDto)
                        .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //게시글 내용 조회
    @GetMapping("/{market-board-id}")
    public ResponseEntity viewBoard (@PathVariable ("market-board-id") long marketBoardId) {
        MarketBoard marketBoard = marketBoardService.viewBoard(marketBoardId);
        MarketBoardResponseDto response = mapper.marketBoardToMarketBoardResponseDto(marketBoard);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //게시글 추가
    @PostMapping
    public ResponseEntity addMarketBoard (@RequestBody MarketBoardSaveDto saveDto) {
        MarketBoard marketBoard = mapper.saveMarketBoardSaveDtoToMarketBoard(saveDto);
//        marketBoard.setMember(memberService.findMember(saveDto.getPostInfo().getUserId()));
        marketBoardService.saveBoard(marketBoard);

        log.info("# 게시글이 등록되었습니다.");
        String message = "게시글이 등록되었습니다.";

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    //게시글 수정
    @PatchMapping("/{market-board-id}")
    public ResponseEntity editMarketBoard (@PathVariable ("market-board-id") Long marketBoardId,
                                 @RequestBody MarketBoardUpdateDto updateDto) {
        updateDto.setMarketBoardId(marketBoardId);
        MarketBoard marketBoard = mapper.updateMarketBoardUpdateDtoToMarketBoard(updateDto);
        marketBoardService.updateBoard(marketBoard);

        log.info("# 게시글이 수정되었습니다.");
        String message = "게시글이 수정되었습니다.";

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //게시글 삭제
    @DeleteMapping("/{market-board-id}")
    public ResponseEntity deleteMarketBoard (@PathVariable ("market-board-id") Long marketBoardId) {
        marketBoardService.deleteBoard(marketBoardId);

        log.info("# 게시글이 삭제되었습니다.");
        String message = "게시글이 삭제되었습니다.";

        return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
    }
}
