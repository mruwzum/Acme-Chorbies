package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Collection;

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


    private Coordinate coordinate;

    private Collection<Chirp> chirps;
    private Collection<Like> likes;
    private Collection<Chirp> myChirps;
    private Collection<Like> myLikes;


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

    @NotBlank
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @NotBlank
    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    @OneToOne
    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @OneToOne
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

    @OneToMany(targetEntity = Like.class, mappedBy = "receiver")
    public Collection<Like> getLikes() {
        return likes;
    }

    public void setLikes(Collection<Like> likes) {
        this.likes = likes;
    }

    @OneToMany(targetEntity = Chirp.class, mappedBy = "sender")
    public Collection<Chirp> getMyChirps() {
        return myChirps;
    }

    public void setMyChirps(Collection<Chirp> myChirps) {
        this.myChirps = myChirps;
    }

    @OneToMany(targetEntity = Like.class, mappedBy = "sender")
    public Collection<Like> getMyLikes() {
        return myLikes;
    }

    public void setMyLikes(Collection<Like> myLikes) {
        this.myLikes = myLikes;
    }

}
