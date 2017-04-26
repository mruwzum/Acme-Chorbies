package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

/**
 * Created by daviddelatorre on 19/4/17.
 */

@Entity
@Access(AccessType.PROPERTY)
public class Manager extends Actor {

    private String company;
    private String vatNumber;
    private CreditCard creditCard;
    private Collection<Event> createdEvents;

    @NotBlank
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @NotBlank
    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Event.class, mappedBy = "owner")
    public Collection<Event> getCreatedEvents() {
        return createdEvents;
    }

    public void setCreatedEvents(Collection<Event> createdEvents) {
        this.createdEvents = createdEvents;
    }
}