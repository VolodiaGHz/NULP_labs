package com.hrynyk.labs.service;

import com.hrynyk.labs.AccountGenerator;
import com.hrynyk.labs.domain.AccountsEntity;
import com.hrynyk.labs.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepo accountRepo;

    public void saveDataFromCsv(){
        String line ="";
        AccountGenerator accountGenerator = new AccountGenerator();
        accountGenerator.generateAccount();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Account.csv"));
            while ((line = br.readLine())!=null){
                String[] data = line.split(" ");
                AccountsEntity accountsEntity = new AccountsEntity();
                accountsEntity.setId(Integer.parseInt(data[0]));
                accountsEntity.setName(data[1]);
                accountsEntity.setLastName(data[2]);
                accountsEntity.setCardNumber(data[3]);
                accountsEntity.setCardPassword(data[4]);
                accountsEntity.setBalance(Integer.parseInt(data[5]));
                accountsEntity.setPhoneNumber(data[6]);
                accountRepo.save(accountsEntity);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<AccountsEntity> findAll(){
        return accountRepo.findAll();
    }

    public AccountsEntity findById(int id) {
        return accountRepo.findById(id);
    }

    public void delete(int id){
           accountRepo.deleteById(id);
    }


}
