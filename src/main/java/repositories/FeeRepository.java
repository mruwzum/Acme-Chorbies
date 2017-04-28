package repositories;

import domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Repository
public interface FeeRepository extends JpaRepository<Fee, Integer> {



}
