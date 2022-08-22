package com.alkemy.ong.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public abstract class ClassUtil<T, ID, R extends JpaRepository<T, ID>> {

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

    protected void save(T t) { repository.save(t); }

    protected List<T> findAll() { return repository.findAll(); }

    protected T findById(ID id, String entity) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(entity + " not found with id: " + id));
    }

    protected void delete(T t) { repository.delete(t); }
}