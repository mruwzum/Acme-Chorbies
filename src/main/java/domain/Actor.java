package domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


import org.hibernate.validator.constraints.SafeHtml;
import org.jsoup.safety.Whitelist.*;

import security.UserAccount;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;

/**
 * Created by daviddelatorre on 28/3/17.
 */

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity {

    private String name;
    private String surname;
    private String email;
    private String phone;
    private UserAccount userAccount;
    private Collection<Chirp> myChirps;
    private Collection<Chirp> chirps;


    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @NotBlank
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    @NotBlank
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Pattern(regexp = "(\\+\\d{1,3}[ -.])?(\\(?\\d+\\)?[ -.]?)+")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @NotNull
    @Valid
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @OneToMany(targetEntity = Chirp.class, mappedBy = "receiver", cascade = CascadeType.ALL)
    public Collection<Chirp> getChirps() {
        return chirps;
    }

    public void setChirps(Collection<Chirp> chirps) {
        this.chirps = chirps;
    }

    @OneToMany(targetEntity = Chirp.class, mappedBy = "sender", cascade = CascadeType.ALL)
    public Collection<Chirp> getMyChirps() {
        return myChirps;
    }

    public void setMyChirps(Collection<Chirp> myChirps) {
        this.myChirps = myChirps;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
