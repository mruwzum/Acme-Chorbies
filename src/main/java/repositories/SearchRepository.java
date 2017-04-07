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


    @Query("select c from Chorbi c where c.age between ?1 and ?9 or c.genre=?2 or c.relationship = ?3 or c.coordinate.city =?5 or c.coordinate.country=?6 or c.coordinate.state=?7 or c.coordinate.province=?8 or c.name like %?4 or c.surname like %?4 or c.description like %?4")
    List<Chorbi> finderQuery(int underage, Genre genre, Relationship relationship, String keyword, String city, String country, String state, String province, int overage);

    @Query("select c from Chorbi c where c.age between ?1 and ?4 and c.genre=?2 and c.relationship=?3")
    List<Chorbi> finderQuery2(int underage, Genre genre, Relationship relationship, int overage);
}
