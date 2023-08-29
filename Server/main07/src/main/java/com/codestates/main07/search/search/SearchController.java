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

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{search-id}")
    public ResponseEntity patchSearch(@PathVariable ("search-id") long searchId,
                                      @RequestBody SearchDto.Patch requestBody) {

        Search search = mapper.searchPatchToSearch(requestBody);
        service.updateSearch(search);
        SearchDto.Response response = mapper.searchToSearchResponse(search);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{search-id}")
    public ResponseEntity getSearch(@PathVariable ("search-id") long searchId) {

        Search search = service.findSearch(searchId);
        SearchDto.Response response = mapper.searchToSearchResponse(search);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getSearches(@Positive @RequestParam int page,
                                      @Positive @RequestParam int size) {

        Page<Search> pageSearches = service.findSearches(page - 1, size);
        List<Search> searches = pageSearches.getContent();
        List<SearchDto.Response> responses = mapper.searchesToSearchResponses(searches);

        return new ResponseEntity<>(
                new SearchDto.MultiResponse<>(responses, pageSearches), HttpStatus.OK);
    }

    @DeleteMapping("/{search-id}")
    public ResponseEntity deleteSearch(@PathVariable ("search-id") long searchId) {
        service.deleteSearch(searchId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
