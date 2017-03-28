package repositories;

import domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
}
