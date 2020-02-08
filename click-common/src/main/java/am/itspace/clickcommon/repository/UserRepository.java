package am.itspace.clickcommon.repository;

import am.itspace.clickcommon.model.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
@SpringBootApplication

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String s);
    User findByToken(String token);
}
