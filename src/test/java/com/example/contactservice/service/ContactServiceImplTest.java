package com.example.contactservice.service;

import com.example.contactservice.model.Contact;
import com.example.contactservice.service.impl.ContactServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

import static org.junit.jupiter.api.Assertions.*;

class ContactServiceImplTest {

    private ContactServiceImpl contactService;
    private CacheManager cacheManager;

    @BeforeEach
    void setUp() {
        cacheManager = new ConcurrentMapCacheManager("contacts");
        contactService = new ContactServiceImpl();

        // Инициализация тестовых данных
        Contact testContact = new Contact();
        testContact.setId(1L);
        testContact.setName("Test");
        testContact.setEmail("test@example.com");
        testContact.setPhone("+123456789");
        contactService.save(testContact);
    }

    @Test
    void testSaveAndFindContact() {
        Contact found = contactService.findById(1L);
        assertNotNull(found);
        assertEquals("Test", found.getName());
        assertEquals("test@example.com", found.getEmail());
    }

    @Test
    void testUpdateContact() {
        Contact updatedContact = new Contact();
        updatedContact.setId(1L);
        updatedContact.setName("Updated");
        updatedContact.setEmail("updated@example.com");
        updatedContact.setPhone("+987654321");

        contactService.update(1L, updatedContact);

        Contact found = contactService.findById(1L);
        assertEquals("Updated", found.getName());
    }

    @Test
    void testDeleteContact() {
        contactService.deleteById(1L);
        assertNull(contactService.findById(1L));
    }
}