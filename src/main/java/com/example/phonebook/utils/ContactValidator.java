package com.example.phonebook.utils;

import com.example.phonebook.entity.Email;
import com.example.phonebook.entity.PhoneNumber;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactValidator {
    ContactValidator() {
    }

    public static boolean validateEmail(List<Email> emailList) {
        final String EMAILREGEX = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(EMAILREGEX);
        if (emailList.isEmpty()) {
            return true;
        }
        for (Email email : emailList) {
            Matcher matcher = pattern.matcher(email.getEmailString());
            if (!matcher.matches()) {
                return false;
            }
        }
        return true;
    }

    public static boolean validatePhoneNumbers(List<PhoneNumber> phoneNumbersList) {
        final String PHONEREGEX = "^[+]?[(]?\\d{3}[)]?[-s.]?\\d{3}[-s.]?\\d{4,6}$";
        Pattern pattern = Pattern.compile(PHONEREGEX);
        for (PhoneNumber phoneNumber : phoneNumbersList) {
            Matcher matcher = pattern.matcher(phoneNumber.getPhone());
            if (!matcher.matches()) {
                return false;
            }
        }
        return true;
    }

}
