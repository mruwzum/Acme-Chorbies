package repositories;

import domain.Administrator;
import domain.Chorbi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

    @Query("select c from Actor c where c.userAccount.id = ?1")
    Administrator findByUserAccountId(int userAccountId);


    //A listing with the number of chorbies per country and city.
    @Query("select c from Chorbi c group by c.coordinate.city")
    Collection<Chorbi> chorbiesPerCity();
    @Query("select c from Chorbi c group by c.coordinate.country")
    Collection<Chorbi> chorbiesPerCountry();

    //The minimum, the maximum, and the average ages of the chorbies.
    Double averageAgesOfTheChorbies();
    Integer maxAgeOfTheChorbies();
    Integer minAgeOfTheChorbies();

    //The ratio of chorbies who have not registered a credit card or have regis- tered an invalid credit card
    Double ratioOfChorbiesWhoHaveNotCreditCardRegistered();


    //The ratios of chorbies who search for ?activities?, ?friendship?, and ?love?.
    Double ratioOfChorbiesWhoSearchActivities();
    Double ratioOfChorbiesWhoSearchFriendship();
    Double ratioOfChorbiesWhoSearchLove();

    //The list of chorbies, sorted by the number of likes they have got

    @Query("select c from Chorbi c order by c.likes.size desc")
    Collection<Chorbi> chorbiesSortedByTheNumberOfLikes();

    //The minimum, the maximum, and the average number of likes per chorbi

    @Query("select avg(likes.size) from Chorbi")
    Double averageNumberOfLikesPerChorbi();
    @Query("select max(likes.size) from Chorbi")
    Integer maxNumberOfLikePerChorbi();
    @Query("select min(likes.size) from Chorbi")
    Integer minNumberOfLikePerChorbi();

    //The minimum, the maximum, and the average number of chirps that a chor- bi receives from other chorbies

    @Query("select avg(chirps.size) from Chorbi")
    Double averageNumberOfChirpsReceived();
    @Query("select max(chirps.size) from Chorbi")
    Integer maxNumberOfChirpsReceived();
    @Query("select min(chirps.size) from Chorbi")
    Integer minNumberOfChirpsReceived();


    //The minimum, the maximum, and the average number of chirps that a chor- bi sends to other chorbies

    @Query("select avg(myChirps.size) from Chorbi")
    Double averageNumberOfChirpsSended();
    @Query("select max(myChirps.size) from Chorbi")
    Integer maxNumberOfChirpsSended();
    @Query("select min(myChirps.size) from Chorbi")
    Integer minNumberOfChirpsSended();


    //The chorbies who have got more chirps
    //TODO en el servicio solo se coge el primer elemento de la colección
    @Query("select c from Chorbi c order by c.chirps.size desc")
    Collection<Chorbi> chorbieWhoHaveMoreChirpsReceived();

    //The chorbies who have sent more chirps
    //TODO en el servicio solo se coge el primer elemento de la colección
    @Query("select c from Chorbi c order by c.myChirps.size desc")
    Collection<Chorbi> chorbieWhoHaveMoreChirpsSended();
}
