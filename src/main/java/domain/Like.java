package domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Like extends DomainEntity {



    private Date moment;
    private String text;


    private Chorbi sender;
    private Chorbi receiver;


    @NotNull
    @Temporal(TemporalType.DATE)
    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne
    public Chorbi getSender() {
        return sender;
    }

    public void setSender(Chorbi sender) {
        this.sender = sender;
    }

    @ManyToOne
    public Chorbi getReceiver() {
        return receiver;
    }

    public void setReceiver(Chorbi receiver) {
        this.receiver = receiver;
    }

}
