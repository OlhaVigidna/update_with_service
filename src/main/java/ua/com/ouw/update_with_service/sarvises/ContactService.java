package ua.com.ouw.update_with_service.sarvises;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.ouw.update_with_service.dao.ContactDAO;
import ua.com.ouw.update_with_service.models.Contact;

import java.util.List;

@Service
public class ContactService {

    private ContactDAO contactDAO;

    public ContactService(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    public void save(Contact contact) {
        if (contact != null) {
            contactDAO.save(contact);
        }
    }

    public List<Contact> findAll() {
        return contactDAO.findAll();
    }

    public List<Contact> findAllByName(String name) {
        if (!name.isEmpty()) {
            return contactDAO.findAllByName(name);
        }
        return null;
    }

    public Contact getOne(int id) {
        return contactDAO.getOne(id);
    }

    public void transferFile(MultipartFile file) {
//        file.transferTo();
    }

}
