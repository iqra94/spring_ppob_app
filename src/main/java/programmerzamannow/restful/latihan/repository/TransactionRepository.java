package programmerzamannow.restful.latihan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import programmerzamannow.restful.latihan.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
