package com.hrynyk.labs.domain;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class AccountsEntity {
    @Id
    private int id;
    @Column(name = "Name")
    private String name;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "CardNumber")
    private String cardNumber;
    @Column(name = "CardPassword")
    private String cardPassword;
    @Column(name = "Balance")
    private  int balance;
    @Column(name = "PhoneNumber")
    private String phoneNumber;


    public AccountsEntity() {
    }

    public AccountsEntity(String name, String lastName, String phoneNumber,
                          int balance, String cardNumber, String cardPassword) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.cardNumber = cardNumber;
        this.cardPassword = cardPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardPassword() {
        return cardPassword;
    }

    public void setCardPassword(String cardPassword) {
        this.cardPassword = cardPassword;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
