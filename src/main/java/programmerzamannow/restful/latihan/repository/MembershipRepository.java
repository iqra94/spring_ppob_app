package programmerzamannow.restful.latihan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import programmerzamannow.restful.latihan.entity.Membership;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

  Membership findByEmail(String email);

}
