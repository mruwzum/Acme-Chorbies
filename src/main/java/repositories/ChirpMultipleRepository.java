package repositories;

import domain.Chirp;
import domain.ChirpMultiple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Repository
public interface ChirpMultipleRepository extends JpaRepository<ChirpMultiple, Integer> {
}
