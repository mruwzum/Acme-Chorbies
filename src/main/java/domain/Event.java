package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

/**
 * Created by daviddelatorre on 19/4/17.
 */

@Entity
@Access(AccessType.PROPERTY)
public class Event extends DomainEntity {

    private String title;
    private Date date;
    private String description;
    private String picture;
    private int numberOfSeats;

    private Collection<Chorbi> partakers;
    private Collection<ChirpMultiple> announcements;

    private Manager owner;

    @NotBlank
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull
    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @URL
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @NotNull
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    public Collection<Chorbi> getPartakers() {
        return partakers;
    }

    public void setPartakers(Collection<Chorbi> partakers) {
        this.partakers = partakers;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Manager getOwner() {
        return owner;
    }

    public void setOwner(Manager owner) {
        this.owner = owner;
    }


    @ManyToMany(cascade = CascadeType.ALL, targetEntity = ChirpMultiple.class)
    public Collection<ChirpMultiple> getAnnouncements() {
        return announcements;
    }
    public void setAnnouncements(Collection<ChirpMultiple> announcements) {
        this.announcements = announcements;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", date=" + date +
                '}';
    }
}
