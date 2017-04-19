package repositories;

import domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by daviddelatorre on 19/4/17.
 */
@Repository
public interface EventRespository extends JpaRepository<Event, Integer> {
}
