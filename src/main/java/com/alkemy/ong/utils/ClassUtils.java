package com.alkemy.ong.utils;

import org.springframework.data.domain.Page;

public abstract class ClassUtils<T, ID> {
    private static final Integer PAGE_SIZE = 10;

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
