package com.example.contactservice.service;

import com.example.contactservice.model.Contact;
import java.util.List;

public interface ContactService {
    List<Contact> findAll();
    Contact findById(Long id);
    Contact save(Contact contact);
    void deleteById(Long id);
    Contact update(Long id, Contact contact);
}
