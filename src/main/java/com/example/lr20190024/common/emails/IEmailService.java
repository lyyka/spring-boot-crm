package com.example.lr20190024.common.emails;

public interface IEmailService {
    IEmailService to(String[] to);

    void send(SimpleMailable simpleMailable);
}
