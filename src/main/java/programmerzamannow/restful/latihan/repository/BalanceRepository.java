package programmerzamannow.restful.latihan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import programmerzamannow.restful.latihan.entity.Balance;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
  Balance findByUserId(Long id);
}
