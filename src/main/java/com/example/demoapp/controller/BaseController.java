package com.example.demoapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demoapp.Entity.BaseEntity;
import com.example.demoapp.service.BaseService;
import com.example.demoapp.utils.ReturnResultUtil;
import com.github.wenhao.jpa.Specifications;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Slf4j
@CacheConfig(cacheNames = "testCache")
public class BaseController<S extends BaseService<T,ID>,T extends BaseEntity<T,ID>,ID extends Serializable> {
    @Autowired
    private S baseManage;
    @Autowired
    private MessageSource messageSource;

    @Cacheable(key = "#root.target +'_'+ #p0 + '_' + #p1")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ReturnResultUtil getList(@RequestParam("pageSize") int pageSize,@RequestParam("pageNum") int pageNum){
        log.info("baseController get list---"+pageSize);
        Specification<T> sSpecification = Specifications.<T>and().eq("deleted",0).build();
        if(pageSize>0){
            Pageable pageable = PageRequest.of(pageSize, pageNum);
            Page<T> page = baseManage.findAll(sSpecification,pageable);
            return ReturnResultUtil.publicOkResp(page.getContent());
        }else if(pageSize == -1){
            return ReturnResultUtil.publicOkResp(baseManage.findAll());
        }else{
            List<T> entities = baseManage.findAll(sSpecification);
            return ReturnResultUtil.publicOkResp(entities);
        }
    }

    //利用PathVariable接收uri模板参数
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ReturnResultUtil findOne(@PathVariable ID id){
        return ReturnResultUtil.publicOkResp(baseManage.findOne(id));
    }

    @RequestMapping(value = "/action",method = RequestMethod.POST)
    public ReturnResultUtil add(@Valid T entity, BindingResult bindingResult){
        log.info("baseController post request--add action");
        if(bindingResult.hasErrors()){
            System.out.println(entity.toString());
            return ReturnResultUtil.error(bindingResult,messageSource);
        }
        log.info("entity toString");
        baseManage.save(entity);
        return ReturnResultUtil.publicOkResp();
    }

    @RequestMapping(value = "/action/{id}",method = RequestMethod.POST)
    public ReturnResultUtil update(@Valid T entity,BindingResult bindingResult,@PathVariable ID id){
        System.out.println("baseController action:update post request");
        if(bindingResult.hasErrors()){
            return ReturnResultUtil.error(bindingResult,messageSource);
        }
        log.info("baseController entity {}"+entity.toString());
        Optional<T> optionalT = baseManage.findOne(id);
        if(optionalT.isPresent()){
            T old = optionalT.get();
            entity.setId(id);
            entity.setDeleted(old.isDeleted());
            baseManage.save(old);
        }
        return ReturnResultUtil.publicOkResp();
    }

    @RequestMapping(value = "/action/{id}",method = RequestMethod.DELETE)
    public ReturnResultUtil delete(@PathVariable ID id){
        log.info("baseController action delete");
        baseManage.setDeletedTrue(id);
        return ReturnResultUtil.publicOkResp();
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ReturnResultUtil search(@RequestBody JSONObject object){
        System.out.println(object.toJSONString());
        log.info("baseController action search post"+object.toString());
        return ReturnResultUtil.publicOkResp(baseManage.search(object));

    }

}
