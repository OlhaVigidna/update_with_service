package ua.com.ouw.update_with_service.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.ouw.update_with_service.dao.UserDAO;
import ua.com.ouw.update_with_service.models.Contact;
import ua.com.ouw.update_with_service.models.Phone;
import ua.com.ouw.update_with_service.models.User;
import ua.com.ouw.update_with_service.sarvises.ContactService;
import ua.com.ouw.update_with_service.sarvises.EmailService;
import ua.com.ouw.update_with_service.sarvises.PhoneService;
import ua.com.ouw.update_with_service.sarvises.editirs.PhoneEditor;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class HomeController {
    private ContactService contactService;
    private PhoneService phoneService;


    @GetMapping({"/", "/home"})
    public String home(Model model
//                       @AuthenticationPrincipal Authentication authentication,
//                       @AuthenticationPrincipal Principal principal,
//                       @AuthenticationPrincipal UserDetails userDetails
    ) {
//        model.addAttribute("contacts", contactService.findAll());
//        model.addAttribute("contact", new Contact("test", "test@test.com"));
//        model.addAttribute("xxx", "hello page");
        return "home";
    }

    @PostMapping("/successURL")
    public String successURL() {
        return "home";
    }


    @PostMapping("/saveContact")
    public String saveContact(@Valid Contact contact, BindingResult bindingResult,
                              @RequestParam("picture") MultipartFile file
    ) {
        if (bindingResult.hasErrors()) {
            return "home";
        }
        contactService.transferFile(file);
        System.out.println(contact.getPhoneList());
        contact.setAvatar(file.getOriginalFilename());
        contactService.save(contact);

        return "redirect:/";
    }

    @Autowired
    private EmailService emailService;

    @PostMapping("/upload")
    public @ResponseBody
    String uploadAjax(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam MultipartFile image
    ) throws MessagingException {
        Contact contact = new Contact(name, email);
        contact.setAvatar(image.getOriginalFilename());
        contactService.transferFile(image);
        contactService.save(contact);

        emailService.sendEmail(email, image);
        return "ok";
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

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/saveUser")
    public String saveUser(User user) {
        String password = user.getPassword();
        String encode = passwordEncoder.encode(password);
        user.setPassword(encode);
        userDAO.save(user);
        return "redirect:/";
    }

}
