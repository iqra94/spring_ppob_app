package programmerzamannow.restful.latihan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import programmerzamannow.restful.latihan.entity.Balance;

@EnableJpaRepositories
public interface BalanceRepository extends JpaRepository<Balance, Long> {
//  Balance findByUserId(Long id);
}
