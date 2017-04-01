package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by daviddelatorre on 1/4/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class SearchCache extends DomainEntity {


    private int cacheValue;



    @NotNull
    public int getCacheValue() {
        return cacheValue;
    }

    public void setCacheValue(int cacheValue) {
        this.cacheValue = cacheValue;
    }
}
