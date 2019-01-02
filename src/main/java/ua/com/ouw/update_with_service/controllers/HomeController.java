package ua.com.ouw.update_with_service.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.ouw.update_with_service.models.Contact;
import ua.com.ouw.update_with_service.models.Phone;
import ua.com.ouw.update_with_service.sarvises.ContactService;
import ua.com.ouw.update_with_service.sarvises.PhoneService;

@Controller
@AllArgsConstructor
public class HomeController {
    private ContactService contactService;
    private PhoneService phoneService;



    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("contacts", contactService.findAll());
        return "home";
    }


    @PostMapping("/saveContact")
    public String saveContact(Contact contact, @RequestParam("phoneList") String num){
        contactService.save(contact);

        Phone phone = new Phone(num);
        phone.setContact(contact);

        phoneService.save(phone);
        return "redirect:/";
    }
}
