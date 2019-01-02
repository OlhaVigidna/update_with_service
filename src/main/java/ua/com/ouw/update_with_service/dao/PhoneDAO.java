package ua.com.ouw.update_with_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.ouw.update_with_service.models.Phone;

//@Repository
public interface PhoneDAO extends JpaRepository<Phone, Integer> {
}
