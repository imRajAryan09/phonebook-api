package com.example.phonebook.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;
    @Column(nullable = false, length = 100)
    String name;


    @Temporal(TemporalType.DATE)
    Date dateOfBirth;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contact", cascade = CascadeType.ALL)
    private List<Email> emailAddressList = new ArrayList<>();

    @JsonManagedReference
    @Column(nullable = false)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contact", cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumbersList = new ArrayList<>();

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", name='" + name + '\'' + ", dateOfBirth=" + dateOfBirth + ", emailAddressList=" + emailAddressList + ", phoneNumbersList=" + phoneNumbersList + '}';
    }
}
