package com.example.phonebook.controller;

import com.example.phonebook.entity.Contact;
import com.example.phonebook.service.ContactService;
import com.example.phonebook.service.EmailService;
import com.example.phonebook.service.PhoneNumberService;
import com.example.phonebook.utils.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    final ContactService contactService;
    final EmailService emailService;
    final PhoneNumberService phoneNumberService;

    public ContactController(ContactService contactService, EmailService emailService, PhoneNumberService phoneNumberService) {
        this.contactService = contactService;
        this.emailService = emailService;
        this.phoneNumberService = phoneNumberService;
    }

    // crud operations
    @GetMapping("/getAll")
    public ResponseEntity<Map<String, List<Contact>>> getContacts() {
        List<Contact> allContacts = contactService.getContacts();
        if (allContacts.isEmpty()) return ResponseEntity.noContent().build();
        Map<String, List<Contact>> allContactsMap = Map.of("contacts", allContacts);
        return ResponseEntity.ok(allContactsMap);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Contact>> getContact(@PathVariable Long id) {
        Contact contact = contactService.getContactById(id);
        Map<String, Contact> contactMap = Map.of("contact[" + id + "]", contact);
        return ResponseEntity.ok(contactMap);
    }

    @PostMapping("/add")
    public Contact addContact(@RequestBody Contact contact) {
        return contactService.addContact(contact);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateContact(@RequestBody Contact contact, @PathVariable Long id) {
        contactService.updateContact(contact, id);
        return ResponseEntity.ok("Contact updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.ok("Contact deleted successfully");
    }

    // searches

    @GetMapping("/search/n/{name}")
    public ResponseEntity<Map<String, List<Contact>>> getContactsByName(@PathVariable String name) {
        List<Contact> searchResult = contactService.findByName(name);
        if (searchResult.isEmpty()) return ResponseEntity.ok(Map.of(Constants.SEARCH_RESULTS, Collections.emptyList()));
        Map<String, List<Contact>> searchResultMap = Map.of(Constants.SEARCH_RESULTS, searchResult);
        return ResponseEntity.ok(searchResultMap);
    }

    @GetMapping("/search/e/{email}")
    public ResponseEntity<Map<String, List<Contact>>> getContactsByEmail(@PathVariable String email) {
        List<Contact> searchResult = emailService.findByEmailAddress(email);
        if (searchResult.isEmpty()) ResponseEntity.ok(Map.of(Constants.SEARCH_RESULTS, Collections.emptyList()));
        Map<String, List<Contact>> searchResultMap = Map.of(Constants.SEARCH_RESULTS, searchResult);
        return ResponseEntity.ok(searchResultMap);
    }

    @GetMapping("/search/p/{phoneNumber}")
    public ResponseEntity<Map<String, Contact>> getContactsByPhoneNumber(@PathVariable String phoneNumber) {
        Contact searchResult = phoneNumberService.findByPhoneNumber(phoneNumber);
        if (searchResult == null) return ResponseEntity.ok(Map.of(Constants.SEARCH_RESULTS, new Contact()));
        Map<String, Contact> searchResultMap = Map.of(Constants.SEARCH_RESULTS, searchResult);
        return ResponseEntity.ok(searchResultMap);
    }
}
