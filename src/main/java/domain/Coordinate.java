package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Coordinate extends DomainEntity {

    private String country;
    private String state;
    private String province;
    private String city;

    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return city + "(" + country + ")";
    }
}
