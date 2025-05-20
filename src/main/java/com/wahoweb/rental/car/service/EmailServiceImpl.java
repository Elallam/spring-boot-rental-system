package com.wahoweb.rental.car.service;

import com.wahoweb.rental.car.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;
    @Async
    @Override
    public void sendEmail(String to, String subject, String message) {
        System.out.println("SendEmail method called " + sender);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setFrom(sender);
        javaMailSender.send(mailMessage);
    }

    @Async
    public void sendVerificationEmail(User user, String token) {
        String verificationUrl = "http://see-kech-car/verify/" + token;
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getLogin());
        mail.setSubject("Confirmation de votre inscription");
        mail.setText("Prière de confirmer votre adresse email en cliquant sur le lien ci-dessous: " + verificationUrl);
        javaMailSender.send(mail);
    }


}
