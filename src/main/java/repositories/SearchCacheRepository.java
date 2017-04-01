package repositories;

import domain.Search;
import domain.SearchCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Repository
public interface SearchCacheRepository extends JpaRepository<SearchCache, Integer> {
}
