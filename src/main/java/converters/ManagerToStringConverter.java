package converters;

import domain.Manager;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Created by daviddelatorre on 19/4/17.
 */
@Component
@Transactional
public class ManagerToStringConverter implements Converter<Manager, String> {

    @Override
    public String convert(Manager actor) {
        Assert.notNull(actor);
        String result;
        result = String.valueOf(actor.getId());
        return result;
    }
}
