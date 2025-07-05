package com.example.contactservice.service.impl;

import com.example.contactservice.model.Contact;
import com.example.contactservice.service.ContactService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ContactServiceImpl implements ContactService {
    private final ConcurrentHashMap<Long, Contact> contacts = new ConcurrentHashMap<>();

    @Override
    @Cacheable("contacts")
    public List<Contact> findAll() {
        return new ArrayList<>(contacts.values());
    }

    @Override
    @Cacheable(value = "contacts", key = "#id")
    public Contact findById(Long id) {
        return contacts.get(id);
    }

    @Override
    @CacheEvict(value = "contacts", allEntries = true)
    public Contact save(Contact contact) {
        contacts.put(contact.getId(), contact);
        return contact;
    }

    @Override
    @CacheEvict(value = "contacts", key = "#id")
    public void deleteById(Long id) {
        contacts.remove(id);
    }

    @Override
    @CacheEvict(value = "contacts", key = "#id")
    public Contact update(Long id, Contact contact) {
        contacts.put(id, contact);
        return contact;
    }
}