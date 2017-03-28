package repositories;

import domain.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

    @Query("select c from Actor c where c.userAccount.id = ?1")
    Administrator findByUserAccountId(int userAccountId);

}
