package com.example.phonebook.service;

import com.example.phonebook.entity.Contact;
import com.example.phonebook.exceptions.BadRequestException;
import com.example.phonebook.repository.PhoneNumberRepository;
import org.springframework.stereotype.Service;


@Service
public class PhoneNumberService {
    final PhoneNumberRepository phoneNumberRepository;

    public PhoneNumberService(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    public Contact findByPhoneNumber(String phoneNumber) {
        try {
            return phoneNumberRepository.findByEmailAddress(phoneNumber);
        } catch (Exception e) {
            throw new BadRequestException("Failed to find contact");
        }
    }
}
