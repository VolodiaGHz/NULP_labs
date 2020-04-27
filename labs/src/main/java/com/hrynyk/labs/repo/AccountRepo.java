package com.hrynyk.labs.repo;

import com.hrynyk.labs.domain.AccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface AccountRepo extends JpaRepository<AccountsEntity, Long> {
    @Query("select a from AccountsEntity a where a.id = ?1")
    AccountsEntity findById(int id);

    @Transactional
    @Modifying
    @Query("delete from AccountsEntity a where a.id = ?1")
    void deleteById(int id);

    @Query("select a from AccountsEntity  a where  a.cardNumber =?1 and a.cardPassword=?2")
    AccountsEntity findAccount(String cardNumber, String cardPassword);

    @Transactional
    @Modifying
    @Query("update AccountsEntity  set  cardPassword = ?3 where cardNumber = ?1 and cardPassword = ?2")
    void changePin(String cardNumber, String cardPassword, String password);

    @Query("select a from AccountsEntity  a where  a.cardNumber=?1")
    AccountsEntity checkCard(String card);

    @Transactional
    @Modifying
    @Query("update AccountsEntity  set  balance = balance - ?1 where cardNumber = ?2 and cardPassword = ?3")
    void sendMoney(int balance, String Ycard, String password);

    @Transactional
    @Modifying
    @Query("update AccountsEntity  set  balance = balance + ?1 where cardNumber = ?2")
    void receiveMoney(int balance, String Rcard);

}
