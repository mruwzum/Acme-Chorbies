package repositories;

import domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

/**
 * Created by daviddelatorre on 19/4/17.
 */
@Repository
public interface EventRespository extends JpaRepository<Event, Integer> {


    @Query("select c from Event c where c.date between ?1 and ?2")
    Collection<Event> getEventBetwenNowAndLastMonth(Date now, Date last);

    @Query("select c from Event c where c.numberOfSeats - c.partakers.size > 0")
    Collection<Event> getEventBetwenWithSeatsAvailable();

    @Query("select c from Event c where c.date < ?1")
    Collection<Event> lastEventInSystem(Date date);
}
