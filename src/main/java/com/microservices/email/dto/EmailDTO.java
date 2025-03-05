package com.microservices.email.dto;


public record EmailDTO(Integer userId, String emailTo, String eventName, String prettyName, String subject, String body) {
}
