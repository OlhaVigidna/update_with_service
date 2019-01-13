package ua.com.ouw.update_with_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.ouw.update_with_service.models.Contact;
import ua.com.ouw.update_with_service.sarvises.ContactService;

import java.util.List;

@RestController
public class CustomAsyncController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/saveAsync")
    public List<Contact> saveAsync(@RequestBody Contact contact) {
        contactService.save(contact);
        System.out.println(contact);
        System.out.println("react");
        return contactService.findAll();
    }
}
