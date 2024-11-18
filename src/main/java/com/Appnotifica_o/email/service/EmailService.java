package com.Appnotifica_o.email.service;

import com.Appnotifica_o.email.enums.StatusEmail;
import com.Appnotifica_o.email.models.Email;
import com.Appnotifica_o.email.repository.EmailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public Email sendEmail(Email email) {
        try{
            email.setSendDateEmail(LocalDateTime.now());
            email.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getMessage());
            this.javaMailSender.send(message);

            email.setStatusEmail(StatusEmail.SEND);
        } catch (MailException e){
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return this.emailRepository.save(email);
        }
    }


}
