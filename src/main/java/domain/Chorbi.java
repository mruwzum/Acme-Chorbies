package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    private int age;
    private Boolean isBanned;
    private int numberOfStars;


    private Coordinate coordinate;
    private Collection<Liked> likes;
    private Collection<Liked> myLikes;
    private Collection<Search> mySearches;
    private Collection<Event> eventsToGo;

    @URL
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Size(max = 300, message = "Exceeds the 300 characters long")
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
    @Range(min=0, max=99)
    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    @OneToOne(cascade = CascadeType.ALL)
    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }


    @OneToMany(targetEntity = Liked.class, mappedBy = "receiver", cascade = CascadeType.ALL)
    public Collection<Liked> getLikes() {
        return likes;
    }

    public void setLikes(Collection<Liked> likes) {
        this.likes = likes;
    }

    @OneToMany(targetEntity = Liked.class, mappedBy = "sender",cascade = CascadeType.ALL)
    public Collection<Liked> getMyLikes() {
        return myLikes;
    }

    public void setMyLikes(Collection<Liked> myLikes) {
        this.myLikes = myLikes;
    }

    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "owner")
    public Collection<Search> getMySearches() {
        return mySearches;
    }

    public void setMySearches(Collection<Search> mySearches) {
        this.mySearches = mySearches;
    }


    public Boolean getBanned() {
        return isBanned;
    }

    public void setBanned(Boolean banned) {
        isBanned = banned;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    public Collection<Event> getEventsToGo() {
        return eventsToGo;
    }

    public void setEventsToGo(Collection<Event> eventsToGo) {
        this.eventsToGo = eventsToGo;
    }


    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(int numberOfStars) {
        this.numberOfStars = numberOfStars;
    }
}
