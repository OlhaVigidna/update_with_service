package ua.com.ouw.update_with_service.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.ouw.update_with_service.models.Contact;
import ua.com.ouw.update_with_service.models.Phone;
import ua.com.ouw.update_with_service.sarvises.ContactService;
import ua.com.ouw.update_with_service.sarvises.PhoneService;
import ua.com.ouw.update_with_service.sarvises.editirs.PhoneEditor;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class HomeController {
    private ContactService contactService;
    private PhoneService phoneService;


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("contacts", contactService.findAll());
        model.addAttribute("contact", new Contact("test", "test@test.com"));
        model.addAttribute("xxx", "hello page");
        return "home";
    }


    @PostMapping("/saveContact")
    public String saveContact(@Valid Contact contact, BindingResult bindingResult,
                              @RequestParam("picture") MultipartFile file
                              ) {
        if (bindingResult.hasErrors()){
            return "home";
        }
        System.out.println(contact.getPhoneList());
        contactService.save(contact);

        return "redirect:/";
    }

    @GetMapping("/details-{xxx}")
    public String contactDetaild(@PathVariable("xxx") int id, Model model) {
        Contact one = contactService.getOne(id);
        model.addAttribute("contact", one);
        return "contactDetails";
    }

    @PostMapping("/update")
    public String updateContact(Contact contact) {
        System.out.println(contact);
        contactService.save(contact);
        return "redirect:/";
    }

    @Autowired
    private PhoneEditor phoneDoctor;

    @InitBinder("contact")
    public void initBainder(WebDataBinder binder) {
        System.out.println("!!!!!!!!!!!!!!!!!!!");
        binder.registerCustomEditor(Phone.class, phoneDoctor);
    }
}
