package programmerzamannow.restful.latihan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import programmerzamannow.restful.latihan.entity.Membership;
import programmerzamannow.restful.model.User;

import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

  Membership findByEmail(String email);

//  Optional<Membership> findByUserEmail(String email);

}
