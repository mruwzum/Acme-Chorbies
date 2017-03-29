package converters;

import domain.Administrator;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Created by daviddelatorre on 29/3/17.
 */@Component
@Transactional
public class AdministratorToStringConverter implements Converter<Administrator, String> {
    @Override
    public String convert(Administrator actor) {
        Assert.notNull(actor);
        String result;
        result = String.valueOf(actor.getId());
        return result;
    }
}
