package com.codestates.main07.search.search;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/searches")
public class SearchController {
    private final SearchService service;
    private final SearchMapper mapper;

    public SearchController(SearchService service, SearchMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postSearch(@RequestBody SearchDto.Post requestBody) {

        Search search = mapper.searchPostToSearch(requestBody);
        service.createSearch(search);
        SearchDto.Response response = mapper.searchToSearchResponse(search);

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{search-id}")
    public ResponseEntity patchSearch(@PathVariable ("search-id") long searchId,
                                      @RequestBody SearchDto.Patch requestBody) {

        Search search = mapper.searchPatchToSearch(requestBody);
        search.setSearchId(searchId);
        service.updateSearch(search);

        // 방금 update한 search 정보 불러오기
        Search newSearch = service.findSearch(searchId);
        SearchDto.Response response = mapper.searchToSearchResponse(newSearch);

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{search-id}")
    public ResponseEntity getSearch(@PathVariable ("search-id") long searchId) {

        Search search = service.findSearch(searchId);
        SearchDto.Response response = mapper.searchToSearchResponse(search);

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getSearches(@Positive @RequestParam int page,
                                      @Positive @RequestParam int size) {

        Page<Search> pageSearches = service.findSearches(page - 1, size);
        List<Search> searches = pageSearches.getContent();
        List<SearchDto.Responses> responses = mapper.searchesToSearchResponses(searches);

        return new ResponseEntity<>(
                new SearchDto.MultiResponse<>(responses, pageSearches, true), HttpStatus.OK);
    }

    @DeleteMapping("/{search-id}")
    public ResponseEntity deleteSearch(@PathVariable ("search-id") long searchId) {
        service.deleteSearch(searchId);
        return new ResponseEntity<>(new SearchDto.SuccessResponse(true), HttpStatus.OK);
    }
}
