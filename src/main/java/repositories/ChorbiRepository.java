package repositories;

import domain.Actor;
import domain.Chorbi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Repository
public interface ChorbiRepository extends JpaRepository<Chorbi, Integer> {

    @Query("select c from Chorbi c where c.userAccount.id = ?1")
    Chorbi findByUserAccountId(int userAccountId);

}
