package com.example.lr20190024.common.utils;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class RandomPassword {

    private int length = 32;

    public RandomPassword length(int length) {
        this.length = length;
        return this;
    }

    public String generate() {
        // ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        // each iteration of the loop randomly chooses a character from the given
        // ASCII range and appends it to the `StringBuilder` instance

        for (int i = 0; i < this.length; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }
}
