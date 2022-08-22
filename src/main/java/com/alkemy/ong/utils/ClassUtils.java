package com.alkemy.ong.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class ClassUtils<T, ID, R extends JpaRepository<T, ID>> {

    @Autowired
    private R repository;
    private static final Integer PAGE_SIZE = 10;

    protected Page<T> getPage(Integer page) {
        return repository.findAll(PageRequest.of(page-1, PAGE_SIZE));
    }

    protected String getPrevious(String path, Integer page) {
        if(page > 1){
            return String.format(path, page-1);
        }
        return null;
    }

    protected String getNext(Page<T> tPage, String path, Integer page) {
        if(tPage.hasNext()){
            return String.format(path, page+1);
        }
        return null;
    }
}
