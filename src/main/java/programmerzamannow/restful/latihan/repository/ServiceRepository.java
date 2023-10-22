package programmerzamannow.restful.latihan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import programmerzamannow.restful.latihan.entity.Banner;
import programmerzamannow.restful.latihan.entity.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
  Service findByServiceCode(String serviceCode);
}
