package service;

import entity.Station;

import java.util.Random;

public class TestService {

    static String generateRandomString(int len) {
        String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
        Random rnd = new Random();
        final StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < len; i++) {
            randomString.append(symbols.charAt(rnd.nextInt(symbols.length())));
        }
        return randomString.toString();
    }



}
