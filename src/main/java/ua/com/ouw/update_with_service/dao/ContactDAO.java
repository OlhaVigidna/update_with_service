package ua.com.ouw.update_with_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.ouw.update_with_service.models.Contact;

import java.util.List;

@Repository
public interface ContactDAO extends JpaRepository<Contact, Integer> {

    List<Contact> findAllByName(String name);

    @Query("select c from Contact c where c.email=:xxx")
    Contact castomRequstasdqweByEmail(@Param("xxx") String email);
}
