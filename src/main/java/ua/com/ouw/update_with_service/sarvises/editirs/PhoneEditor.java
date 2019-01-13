package ua.com.ouw.update_with_service.sarvises.editirs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.ouw.update_with_service.models.Phone;
import ua.com.ouw.update_with_service.sarvises.PhoneService;

import java.beans.PropertyEditorSupport;

@Component
public class PhoneEditor  extends PropertyEditorSupport{

    @Autowired
    private PhoneService phoneService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
//        System.out.println(text + "!!!!######");
        Phone phone = new Phone();
        phone.setNumber(text);
        phoneService.save(phone);
        setValue(phone);
    }
}
