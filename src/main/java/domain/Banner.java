package domain;

import org.hibernate.validator.constraints.URL;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Banner extends DomainEntity {


    public String url;

    // Relationships ---------------------------------------------------------



    //Constructor
    public Banner(){
        super();
    }


    @NotNull
    @URL
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
