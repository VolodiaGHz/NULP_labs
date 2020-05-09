package com.hrynyk.labs;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AccountGenerator {
    Random random = new Random();
    static final File NAMES = new File("Names.csv");
    static final File SURNAMES = new File("LastNames.csv");
    static final String CARD_NUMBER = "4901";
    static final String PHONE_NUMBER = "+380";
    List<String> names;
    List<String> surnames;
    int id = 1;



    {
        try {
            names = Files.readAllLines(NAMES.toPath(), Charset.defaultCharset());
            surnames = Files.readAllLines(SURNAMES.toPath(), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String generateName(){
        String name = names.get(random.nextInt(names.size()));
        return name;
    }

    public String generateSurname(){
        String surname = surnames.get(random.nextInt(surnames.size()));
        return surname;
    }

    public String generateCard(){
        String cardNumber = "";
        cardNumber= cardNumber + CARD_NUMBER;
        for(int i = 4; i<16; i++){
            cardNumber = cardNumber+random.nextInt(9);
        }
        return cardNumber;
    }

    public String generatePin(){
        String pin = "";
        for(int i = 0; i!=4;i++){
            pin = pin + random.nextInt(9);
        }
        return pin;
    }

    public String generatePhone(){
        String phoneNumber = "";
        phoneNumber= phoneNumber + PHONE_NUMBER;
        for(int i = 0; i<9; i++){
            phoneNumber = phoneNumber+random.nextInt(9);
        }
        return phoneNumber;
    }

    public void generateAccount(){
        try {
            PrintWriter writer = new PrintWriter("Account.csv", "UTF-8");
            for(int i = 0;i<=1000; i++){
                writer.println(generateName() + " " +  generateSurname() + " " +
                        generateCard() + " " + generatePin() + " " + 10000 + " " + generatePhone());

            }
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
