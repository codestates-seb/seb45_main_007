package com.codestates.main07.search.search;

import com.codestates.main07.search.exception.BusinessLogicException;
import com.codestates.main07.search.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class SearchService {
    private final SearchRepository searchRepository;

    public SearchService(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public Search createSearch(Search search) {
        return searchRepository.save(search);
    }

    public Search updateSearch(Search search) {
        Search findSearch = findVerifiedSearch(search.getSearchId());

        Optional.ofNullable(search.getTitle())
                .ifPresent(findSearch::setTitle);
        Optional.ofNullable(search.getContent())
                .ifPresent(findSearch::setContent);
        Optional.ofNullable(search.getCategory())
                .ifPresent(findSearch::setCategory);

        findSearch.setModifiedAt(LocalDateTime.now());

        return findSearch;
    }

    public Search findSearch(long searchId) {
        return findVerifiedSearch(searchId);
    }

    public Page<Search> findSearches(int page, int size) {
        return searchRepository.findAll(PageRequest.of(page, size,
                Sort.by("searchId").descending()));
    }

    public void deleteSearch(long searchId) {
        Search findSearch = findVerifiedSearch(searchId);
        searchRepository.delete(findSearch);
    }

    private Search findVerifiedSearch(long searchId) {
        Optional<Search> optionalSearch = searchRepository.findById(searchId);

        return optionalSearch.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.SEARCH_NOT_FOUND));
    }
}
