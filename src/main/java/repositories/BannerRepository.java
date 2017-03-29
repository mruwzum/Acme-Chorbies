package repositories;

import domain.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by daviddelatorre on 10/3/17.
 */
@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {

}
