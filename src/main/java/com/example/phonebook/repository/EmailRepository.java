package com.example.phonebook.repository;

import com.example.phonebook.entity.Contact;
import com.example.phonebook.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {
    @Query("SELECT e.contact FROM Email e WHERE e.emailString=:emailAddress")
    List<Contact> findByEmailAddress(@Param("emailAddress") String emailAddress);
}
