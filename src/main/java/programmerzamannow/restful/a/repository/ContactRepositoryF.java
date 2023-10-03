package programmerzamannow.restful.a.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import programmerzamannow.restful.entity.Contact;
import programmerzamannow.restful.entity.User;

import java.util.Optional;

@Repository
public interface ContactRepositoryF extends JpaRepository<Contact, String>, JpaSpecificationExecutor<Contact> {

    Optional<Contact> findFirstByUserAndId(User user, String id);

}

/** sebelum 02:39:00 - Get Contact API
 @Repository
 public interface ContactRepositoryF extends JpaRepository<Contact, String> {
 }
 */


/** sebelum 02:57:09 - Search Contact API
 @Repository
 public interface ContactRepositoryF extends JpaRepository<Contact, String> {

 Optional<Contact> findFirstByUserAndId(User user, String id);

 }
 */