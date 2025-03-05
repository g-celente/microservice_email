package com.microservices.email.model;

import com.microservices.email.enums.StatusEmail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="tb_email")
@Getter
@Setter
public class Email {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="email_id")
    private UUID emailId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "email_from", nullable = false)
    private String emailFrom;

    @Column(name = "email_to", nullable = false)
    private String emailTo;

    @Column(name = "event_pretty_name", nullable = false)
    private String prettyName;

    private String subject;
    private String body;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;

}
