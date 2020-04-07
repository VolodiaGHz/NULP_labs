package com.hrynyk.labs.controller;

import com.hrynyk.labs.service.AccountService;
import com.hrynyk.labs.domain.AccountsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppController {

    @Autowired
    AccountService service;

    @GetMapping
    public List<AccountsEntity> getAll(){
        return  service.findAll();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public AccountsEntity getById(@PathVariable int id){
        return service.findById(id);
    }

    @RequestMapping(path = "/csvinput")
    public void inputDataToDb(){
        service.saveDataFromCsv();
    }

    @RequestMapping(path="/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id){
          service.delete(id);
          return "Account with id: "+ id +" delete successfully" ;
    }

    @RequestMapping(path = "/posts")
    public String post(){
        return "Sorry, post methods dont work";
    }

    @RequestMapping(path = "/put")
    public String put(){
        return "";
    }
}
