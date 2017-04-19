package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by daviddelatorre on 19/4/17.
 */

@Entity
@Access(AccessType.PROPERTY)
public class Manager extends Actor {

    String company;
    String vatNumber;
    CreditCard creditCard;

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
}
