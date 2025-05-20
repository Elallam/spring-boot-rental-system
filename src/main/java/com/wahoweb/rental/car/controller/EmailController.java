package com.wahoweb.rental.car.controller;

import com.wahoweb.rental.car.entity.Comment;
import com.wahoweb.rental.car.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    @Autowired
    private EmailService emailService;
    @PostMapping
    public void sendEmail(@RequestBody Comment commentaire){
        if(commentaire.getTele() == null || commentaire.getNom() == null || commentaire.getComment() == null){
            String message = "Message envoyé par "+ commentaire.getEmail();
            System.out.print("email sent " + message);
            emailService.sendEmail("seekechcar@gmail.com", "Notification", message);
        }else{
            String message = "Message envoyé par "+ commentaire.getNom() + "\nEmail: " + commentaire.getEmail() + "\nTèlèphone: " + commentaire.getTele() + "\nCommentaire: " + commentaire.getComment();
            System.out.print("email sent " + message);
            emailService.sendEmail("seekechcar@gmail.com", "Notification", message);
        }
    }
}
