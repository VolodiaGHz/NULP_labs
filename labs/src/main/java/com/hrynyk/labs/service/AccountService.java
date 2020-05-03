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
//                accountsEntity.setId(Integer.parseInt(data[0]));
                accountsEntity.setName(data[0]);
                accountsEntity.setLastName(data[1]);
                accountsEntity.setCardNumber(data[2]);
                accountsEntity.setCardPassword(data[3]);
                accountsEntity.setBalance(Integer.parseInt(data[4]));
                accountsEntity.setPhoneNumber(data[5]);
                accountRepo.save(accountsEntity);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crete(String name, String lastName, String phone){
        AccountGenerator accountGenerator = new AccountGenerator();
        AccountsEntity entity = new AccountsEntity();
        entity.setName(name);
        entity.setLastName(lastName);
        entity.setPhoneNumber(phone);
        entity.setCardNumber(accountGenerator.generateCard());
        entity.setCardPassword(accountGenerator.generatePin());
        entity.setBalance(10000);
        accountRepo.save(entity);

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

    public AccountsEntity findAccount(String cardNumber, String password){
        return accountRepo.findAccount(cardNumber, password);
    }
    public void saveData(AccountsEntity accountsEntity){
        accountRepo.save(accountsEntity);
    }

    public void changePassword(String card, String Opassword, String Npassword){
        accountRepo.changePin(card, Opassword, Npassword);
    }

    public boolean checkData(String senderCard, String receiverCard, int Sum, String password){
        if(accountRepo.checkCard(senderCard)!=null && accountRepo.checkCard(receiverCard) != null){
            makeTransaction(Sum, senderCard, receiverCard, password);
            return true;
        }else {
            return false;
        }
    }

    public void makeTransaction(int Sum, String senderCard, String receiverCard, String password){
        accountRepo.sendMoney(Sum, senderCard, password);
        accountRepo.receiveMoney(Sum, receiverCard);
    }


}
