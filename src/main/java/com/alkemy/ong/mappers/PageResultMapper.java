package com.alkemy.ong.mappers;

import com.alkemy.ong.dto.response.pagination.PageResultResponse;

import java.util.List;

public class PageResultMapper<T> {

    public PageResultResponse<T> mapPage(List<T> content, String previous, String next){
        PageResultResponse<T> pageResult = new PageResultResponse<>();
        pageResult.setContent(content);
        pageResult.setNext_page_url(next);
        pageResult.setPrevious_page_url(previous);
        return pageResult;
    }
}
