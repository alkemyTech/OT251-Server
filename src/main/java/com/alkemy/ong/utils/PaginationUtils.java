package com.alkemy.ong.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public class PaginationUtils {
    private Integer page;
    private static final Integer PAGE_SIZE = 10;
    private String path;
    private Page<?> pageObject;

    public PaginationUtils(JpaRepository repository, Integer page, String path) {
        this.page = page;
        this.path = path;
        this.pageObject = repository.findAll(PageRequest.of(page-1, PAGE_SIZE));
    }

    public Page<?> getPage() {
        return pageObject;
    }

    public String getPrevious() {
        if(page > 1){
            return String.format(path, page-1);
        }
        return null;
    }

    public String getNext() {
        if(pageObject.hasNext()){
            return String.format(path, page+1);
        }
        return null;
    }
}