package ua.com.ouw.update_with_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.ouw.update_with_service.models.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

    User findAllByUsername(String username);
}
