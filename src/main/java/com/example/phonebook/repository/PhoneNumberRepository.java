package com.example.phonebook.repository;

import com.example.phonebook.entity.Contact;
import com.example.phonebook.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
    @Query("SELECT p.contact FROM PhoneNumber p WHERE p.phone=:phoneNumber")
    Contact findByEmailAddress(@Param("phoneNumber") String phoneNumber);
}
