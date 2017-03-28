package repositories;

import domain.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
}
