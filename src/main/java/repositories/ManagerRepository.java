package repositories;

import domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by daviddelatorre on 19/4/17.
 */
@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    @Query("select c from Manager c where c.userAccount.id = ?1")
    Manager findByUserAccountId(int userAccountId);

}
