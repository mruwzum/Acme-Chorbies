package repositories;

import domain.Chirp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Repository
public interface ChirpRepository extends JpaRepository<Chirp, Integer> {
}
