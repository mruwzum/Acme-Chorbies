package repositories;

import domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Repository
public interface SearchRepository extends JpaRepository<Search, Integer> {




    @Query("select c from Chorbi c where c.age between ?1 and ?2")
    List<Chorbi> findByAge(int underage, int overage);

    @Query("select c from Chorbi c where c.genre=?1")
    List<Chorbi> findByGenre(Genre genre);

    @Query("select c from Chorbi c where c.relationship=?1")
    List<Chorbi> findByRelationship(Relationship relationship);

    @Query("select c from Chorbi c where c.description like %?1")
    List<Chorbi> findByKeyword(String keyword);

    @Query("select c from Chorbi c where c.coordinate.city = ?1")
    List<Chorbi> findByCity(String city);

    @Query("select c from Chorbi c where c.coordinate.state = ?1")
    List<Chorbi> findByState(String state);

    @Query("select c from Chorbi c where c.coordinate.country = ?1")
    List<Chorbi> findByCountry(String country);

    @Query("select c from Chorbi c where c.coordinate.province = ?1")
    List<Chorbi> findByProvince(String province);


}
