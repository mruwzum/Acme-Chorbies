package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Chirp extends DomainEntity {

    private String message;
    private String subject;
    private Date moment;
    Collection<String> attachments;

    Chorbi sender;
    Chorbi receiver;


    @NotBlank
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @NotBlank
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    @NotNull
    @Temporal(TemporalType.DATE)
    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    @ElementCollection
    public Collection<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(Collection<String> attachments) {
        this.attachments = attachments;
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
