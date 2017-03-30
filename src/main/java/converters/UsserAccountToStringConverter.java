package converters;

import domain.CreditCard;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import security.UserAccount;

/**
 * Created by daviddelatorre on 29/3/17.
 */@Component
@Transactional
public class UsserAccountToStringConverter implements Converter<UserAccount, String> {

    @Override
    public String convert(UserAccount actor) {
        Assert.notNull(actor);
        String result;
        result = String.valueOf(actor.getId());
        return result;
    }
}
