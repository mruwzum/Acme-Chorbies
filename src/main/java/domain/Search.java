package domain;

import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Search extends DomainEntity {


    private Integer age;
    private Relationship relationship;
    private Genre genre;
    private Coordinate coordinate;
    private String keyword;
    private Chorbi owner;
    private Date creationDate;


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    @OneToOne(cascade = CascadeType.ALL)
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @ManyToOne
    public Chorbi getOwner() {
        return owner;
    }

    public void setOwner(Chorbi owner) {
        this.owner = owner;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
