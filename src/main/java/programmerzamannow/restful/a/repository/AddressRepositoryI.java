package programmerzamannow.restful.a.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import programmerzamannow.restful.entity.Address;
import programmerzamannow.restful.entity.Contact;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepositoryI extends JpaRepository<Address, String> {

    Optional<Address> findFirstByContactAndId(Contact contact, String id);

    List<Address> findAllByContact(Contact contact);

}

/** sebelum 03:32:10 - Get Address API
 @Repository
 public interface AddressRepositoryI extends JpaRepository<Address, String> {
 }
 */

/** sebelum 03:48:19 - List Address API
 @Repository
 public interface AddressRepositoryI extends JpaRepository<Address, String> {

 Optional<Address> findFirstByContactAndId(Contact contact, String id);

 }
 */