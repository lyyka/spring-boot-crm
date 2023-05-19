package com.example.lr20190024.users.emails;

import com.example.lr20190024.common.emails.SimpleMailable;

public class UserCreatedEmail extends SimpleMailable {
    private final String email;
    
    private final String password;

    public UserCreatedEmail(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String getSubject() {
        return "Account created";
    }

    @Override
    public String getText() {
        return "Your account is created. Your email is " + this.email + " and your password is " + password;
    }
}
