package domain;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class CreditCard extends DomainEntity {




    private String holder;
    private String number;
    private int ExpirationYear;
    private int ExpirationMonth;
    private String CVV;
    private Boolean valid;


    private Brand brand;

    @SafeHtml
    @NotBlank
    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    @NotNull
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }


    @CreditCardNumber
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @NotNull
    public int getExpirationYear() {
        return ExpirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        ExpirationYear = expirationYear;
    }

    @NotNull
    public int getExpirationMonth() {
        return ExpirationMonth;
    }

    public void setExpirationMonth(int expirationMonth) {
        ExpirationMonth = expirationMonth;
    }

    @Pattern(regexp = "\\d{3}$")
    @NotNull
    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
