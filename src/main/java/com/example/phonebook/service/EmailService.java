package com.example.phonebook.service;

import com.example.phonebook.entity.Contact;
import com.example.phonebook.exceptions.BadRequestException;
import com.example.phonebook.repository.EmailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    final EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<Contact> findByEmailAddress(String emailAddress) {
        try {
            return emailRepository.findByEmailAddress(emailAddress);
        } catch (Exception e) {
            throw new BadRequestException("Failed to find contact");
        }
    }
}
