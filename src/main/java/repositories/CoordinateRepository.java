package repositories;

import domain.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Repository
public interface CoordinateRepository extends JpaRepository<Coordinate, Integer> {
}
