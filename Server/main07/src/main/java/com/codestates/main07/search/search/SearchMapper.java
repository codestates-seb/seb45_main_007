package com.codestates.main07.search.search;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SearchMapper {
    Search searchPostToSearch(SearchDto.Post requestBody);
    Search searchPatchToSearch(SearchDto.Patch requestBody);
    SearchDto.Response searchToSearchResponse(Search search);
    List<SearchDto.Response> searchesToSearchResponses(List<Search> searches);
}
