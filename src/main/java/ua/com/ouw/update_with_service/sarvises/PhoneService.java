package ua.com.ouw.update_with_service.sarvises;

import org.springframework.stereotype.Service;
import ua.com.ouw.update_with_service.dao.PhoneDAO;
import ua.com.ouw.update_with_service.models.Phone;

@Service
public class PhoneService {
    private PhoneDAO phoneDAO;

    public PhoneService(PhoneDAO phoneDAO) {
        this.phoneDAO = phoneDAO;
    }

    public void save(Phone phone){
        phoneDAO.save(phone);
    }
}
