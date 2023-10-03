package programmerzamannow.restful.a.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import programmerzamannow.restful.entity.User;

import java.util.Optional;

@Repository
public interface UserRepositoryA extends JpaRepository<User, String> {

    Optional<User> findFirstByToken(String token);
}

/**
 @Repository
 public interface UserRepositoryA extends JpaRepository<User, String> {
 }
 */
