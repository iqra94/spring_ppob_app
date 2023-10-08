package programmerzamannow.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import programmerzamannow.restful.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
