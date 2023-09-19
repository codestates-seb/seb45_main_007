package com.codestates.main07.marketBoard.board;

import com.codestates.main07.exception.BusinessLogicException;
import com.codestates.main07.exception.ExceptionCode;
import com.codestates.main07.marketBoard.board.domain.MarketBoard;
import com.codestates.main07.marketBoard.board.domain.Tag;
import com.codestates.main07.marketBoard.board.dto.*;
import com.codestates.main07.marketBoard.photo.PhotoService;
import com.codestates.main07.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RequiredArgsConstructor
@Slf4j
@Validated
@PreAuthorize("isAuthenticated()")
@RequestMapping("/marketBoards")
@RestController
public class MarketBoardController {
    private final MarketBoardService marketBoardService;
    private final MarketBoardMapper mapper;
    private final PhotoService photoService;
    private final MemberService memberService;

    /**
     * 게시글 목록 조회
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity<List<MarketBoardResponse>> marketBoardList(Pageable pageable) {
        Page<MarketBoard> marketBoardPage = marketBoardService.boardList(pageable);
        List<MarketBoardResponse> response = marketBoardPage.getContent()
                .stream()
                .map(mapper::marketBoardToMarketBoardResponseDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 마이페이지의 내가 쓴 글 목록 조회
     * @param memberId
     * @param
     * @param
     * @return
     */
    @GetMapping("/myPage/{memberId}")
    public ResponseEntity myMarketBoardList (@PathVariable ("memberId") long memberId,
                                             @Positive @RequestParam int page,
                                             @Positive @RequestParam int size) {
        Page<MarketBoard> marketBoardPage = marketBoardService.myBoardList(page - 1, size, memberId);
        List<MarketBoardResponse> response = marketBoardPage.getContent()
                .stream()
                .map(mapper::marketBoardToMarketBoardResponseDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(
                new MarketBoardMultiResponseDto<>(response, marketBoardPage, true), HttpStatus.OK);
    }

    /**
     * 게시글 내용 조회 및 조회수 증가
     * @param marketBoardId
     * @return
     */
    @GetMapping("/{marketBoardId}")
    public ResponseEntity viewBoard (@PathVariable ("marketBoardId") long marketBoardId) {

        MarketBoard marketBoard = marketBoardService.viewBoard(marketBoardId);

        marketBoard.incrementViewCount();

//        int updatedViewCount = marketBoard.getViewCount() + 1;
//        marketBoard.updateView(updatedViewCount);

        MarketBoardResponse response = mapper.marketBoardToMarketBoardResponseDto(marketBoard);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 게시글 추가
     * @param createDto
     * @return
     */
    @PostMapping
    public ResponseEntity addMarketBoard (@RequestBody MarketBoardCreate createDto) {
        MarketBoard marketBoard = mapper.createDtoToMarketBoard(createDto);

        marketBoard.setTag(createDto.getTag());

        marketBoard.setMember(memberService.viewMember(createDto.getMemberId()));

//        MarketBoard board = MarketBoard.builder()
//                .member(memberService.viewMember(createDto.getMemberId()))
//                .build();

        MarketBoard createdMarketBoard = marketBoardService.createBoard(marketBoard);
        MarketBoardResponse response = mapper.marketBoardToMarketBoardResponseDto(createdMarketBoard);

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * 게시글 수정
     * @param marketBoardId
     * @param updateDto
     * @return
     */
    @PutMapping("/{marketBoardId}")
    public ResponseEntity editMarketBoard (@PathVariable ("marketBoardId") long marketBoardId,
                                 @RequestBody MarketBoardUpdate updateDto) {
        updateDto.setMarketBoardId(marketBoardId);
        MarketBoard marketBoard = mapper.updateDtoToMarketBoard(updateDto);


        MarketBoard updatedMarketBoard = marketBoardService.updateBoard(marketBoard);
        MarketBoardResponse response = mapper.marketBoardToMarketBoardResponseDto(updatedMarketBoard);

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 게시글 삭제
     * @param marketBoardId
     * @return
     */
    @DeleteMapping("/{marketBoardId}")
    public ResponseEntity deleteMarketBoard (@PathVariable ("marketBoardId") long marketBoardId) {
        marketBoardService.deleteBoard(marketBoardId);

        return new ResponseEntity<>(new SuccessDto(true), HttpStatus.OK);
    }

    /**
     * 이미지 파일 업로드
     * @param photo
     * @param model
     * @return
     */
    @PostMapping("/photos")
    public String uploadPhoto(@RequestParam("photo") MultipartFile photo, Model model) {

        try {
            photoService.insertPhoto(photo);
            model.addAttribute("message", "사진이 등록되었습니다");
            return "photo";
        } catch (Exception e) {
//            throw new BusinessLogicException(ExceptionCode.PHOTO_UPLOAD_FAILED);
            throw new BusinessLogicException(ExceptionCode.UNKNOWN_ERROR);
        }
    }

    @GetMapping("/photos")
    public String photoForm() {
        return "photoForm";  // photoForm.html 이라는 이름의 템플릿을 사용한다고 가정
    }
}
