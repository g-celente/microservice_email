package com.microservices.email.service;

import com.microservices.email.enums.StatusEmail;
import com.microservices.email.model.Email;
import com.microservices.email.repository.EmailRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    final EmailRepository emailRepository;
    final JavaMailSender mailSender;
    final SimpleMailMessage message;

    public EmailService(EmailRepository emailRepository, JavaMailSender mailSender) {
        this.emailRepository = emailRepository;
        this.mailSender = mailSender;
        this.message = new SimpleMailMessage();
    }

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public Email sendEmail(Email email) {
        try {
             email.setSendDateEmail(LocalDateTime.now());
             email.setEmailFrom(emailFrom);

             message.setTo(email.getEmailTo());
             message.setSubject(email.getSubject());
             message.setText(email.getBody());
             mailSender.send(message);

             email.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e) {
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(email);
        }
    }
}
