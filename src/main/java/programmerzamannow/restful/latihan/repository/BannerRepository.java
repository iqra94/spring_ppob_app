package programmerzamannow.restful.latihan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import programmerzamannow.restful.latihan.entity.Banner;

public interface BannerRepository extends JpaRepository<Banner, Long> {
}
