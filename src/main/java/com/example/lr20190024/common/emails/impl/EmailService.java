package com.example.lr20190024.common.emails.impl;

import com.example.lr20190024.common.emails.IEmailService;
import com.example.lr20190024.common.emails.SimpleMailable;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {

    private final MailSender mailSender;
    private String[] to;

    private void sendSimpleMessage(String[] to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        this.mailSender.send(message);
    }

    @Override
    public IEmailService to(String[] to) {
        this.to = to;
        return this;
    }

    public void send(SimpleMailable simpleMailable) {
        this.sendSimpleMessage(
                this.to,
                simpleMailable.getSubject(),
                simpleMailable.getText()
        );
    }
}
