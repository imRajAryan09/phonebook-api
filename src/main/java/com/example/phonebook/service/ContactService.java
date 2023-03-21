package com.example.phonebook.service;

import com.example.phonebook.entity.Contact;
import com.example.phonebook.entity.Email;
import com.example.phonebook.entity.PhoneNumber;
import com.example.phonebook.exceptions.BadRequestException;
import com.example.phonebook.exceptions.InvalidContactException;
import com.example.phonebook.repository.ContactRepository;
import com.example.phonebook.utils.Constants;
import com.example.phonebook.utils.ContactValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact addContact(Contact contact) {
        if (contact.getName() == null || contact.getName().isEmpty()) {
            throw new InvalidContactException("Enter the name doofus");
        }
        List<Email> emailList = contact.getEmailAddressList();
        if (!ContactValidator.validateEmail(emailList)) {
            throw new InvalidContactException("Don't fuck with the email address");
        }
        List<PhoneNumber> phoneNumbersList = contact.getPhoneNumbersList();
        if (!ContactValidator.validatePhoneNumbers(phoneNumbersList)) {
            throw new InvalidContactException("Don't fuck with the phone numbers");
        }
        return contactRepository.save(contact);
    }

    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    public Contact getContactById(Long id) {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (contactOptional.isEmpty()) throw new InvalidContactException(Constants.CONTACT_NOT_FOUND);
        return contactOptional.get();
    }

    public void updateContact(Contact contact, Long id) {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (contactOptional.isEmpty()) throw new InvalidContactException(Constants.CONTACT_NOT_FOUND);
        contact.setId(id);
        try {
            contactRepository.save(contact);
        } catch (Exception e) {
            throw new BadRequestException("Failed to save contact");
        }
    }

    public void deleteContact(Long id) {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (contactOptional.isEmpty()) throw new InvalidContactException(Constants.CONTACT_NOT_FOUND);
        try {
            contactRepository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException("Failed to delete contact");
        }
    }

    public List<Contact> findByName(String name) {
        try {
            return contactRepository.findByName(name);
        } catch (Exception e) {
            throw new BadRequestException("Failed to find contact");
        }
    }

}
