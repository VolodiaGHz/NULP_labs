package com.hrynyk.labs.controller;

import com.hrynyk.labs.AccountGenerator;
import com.hrynyk.labs.service.AccountService;
import com.hrynyk.labs.domain.AccountsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class AppController {

    @Autowired
    AccountService service;

    @GetMapping
    public String loginScreen(){
        return "loginScreen";
    }



    @PostMapping("/loginAccount")
    public String getAccount(@RequestParam String card,
                             @RequestParam String password, Map<String, Object> model){
        AccountsEntity accountsEntity = service.findAccount(card, password);
        model.put("accountView", accountsEntity);

        return "account";
    }

    @GetMapping("/all")
    public List<AccountsEntity> getAll(){
        return  service.findAll();
    }

    @RequestMapping("/passwordChange")
    public String changePin(@RequestParam String Opassword,  @RequestParam String Npassword, @RequestParam String cardN){
        service.changePassword(cardN, Opassword, Npassword);
        return "loginScreen";
    }

    @RequestMapping("/transaction")
    public String makeTransaction(@RequestParam String Ycard, @RequestParam String Rcard,
                                @RequestParam int Sum, @RequestParam String password){
        if(!service.checkData(Ycard, Rcard, Sum, password)){
            return "error";
        }
        return "loginScreen";
    }

//    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
//    public AccountsEntity getById(@PathVariable int id){
//        return service.findById(id);
//    }

    @RequestMapping(path = "/csvinput")
    public void inputDataToDb(){
        service.saveDataFromCsv();
    }
//
//    @RequestMapping(path="/delete/{id}", method = RequestMethod.GET)
//    public String delete(@PathVariable int id){
//          service.delete(id);
//          return "Account with id: "+ id +" delete successfully" ;
//    }
//
//    @RequestMapping(path = "/posts")
//    public String post(){
//        return "Sorry, post methods dont work";
//    }
//
//    @RequestMapping(path = "/put")
//    public String put(){
//        return "";
//    }


}
