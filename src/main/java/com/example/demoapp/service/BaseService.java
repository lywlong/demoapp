package com.example.demoapp.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demoapp.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class BaseService<T,ID extends Serializable> {

    @Autowired
    private BaseRepository<T,ID> baseRepository;

    public void save(T entity){
        Object obj = baseRepository.save(entity);
        System.out.println(obj);
    }

    public int setDeletedTrue(ID id){
        return baseRepository.setDeletedTrue(id);
    }

    public void del(ID id){
        baseRepository.deleteById(id);
    }
    public Optional<T> findOne(ID id){
        return baseRepository.findById(id);
    }

    public List<T> findAll(){
        return baseRepository.findAll();
    }

    public Object search(JSONObject object){
        return baseRepository.findAll();
    }

    public List<T> findAll(Specification<T> specification){
        return baseRepository.findAll(specification);
    }

    public Page<T> findAll(Specification<T> specification, Pageable pageable){
        return baseRepository.findAll(specification,pageable);
    }


}
