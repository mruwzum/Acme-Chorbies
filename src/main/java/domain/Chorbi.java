package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Chorbi extends Actor {

    private String picture;
    private String description;
    private Genre genre;
    private Relationship relationship;
    private CreditCard creditCard;
    private Search search;
    private Date birthDate;


    private Coordinate coordinate;

    private Collection<Chirp> chirps;
    private Collection<Liked> likes;
    private Collection<Chirp> myChirps;
    private Collection<Liked> myLikes;

    @URL
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }


    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    @NotNull
    @Temporal(TemporalType.DATE)
    public Date getBirthDate() {return birthDate;}

    public void setBirthDate(Date birthDate) {this.birthDate = birthDate;}

    @OneToOne(cascade = CascadeType.PERSIST)
    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @OneToOne(cascade = CascadeType.PERSIST)
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @OneToOne
    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    @OneToMany(targetEntity = Chirp.class, mappedBy = "receiver")
    public Collection<Chirp> getChirps() {
        return chirps;
    }

    public void setChirps(Collection<Chirp> chirps) {
        this.chirps = chirps;
    }

    @OneToMany(targetEntity = Liked.class, mappedBy = "receiver")
    public Collection<Liked> getLikes() {
        return likes;
    }

    public void setLikes(Collection<Liked> likes) {
        this.likes = likes;
    }

    @OneToMany(targetEntity = Chirp.class, mappedBy = "sender")
    public Collection<Chirp> getMyChirps() {
        return myChirps;
    }

    public void setMyChirps(Collection<Chirp> myChirps) {
        this.myChirps = myChirps;
    }

    @OneToMany(targetEntity = Liked.class, mappedBy = "sender")
    public Collection<Liked> getMyLikes() {
        return myLikes;
    }

    public void setMyLikes(Collection<Liked> myLikes) {
        this.myLikes = myLikes;
    }

}
