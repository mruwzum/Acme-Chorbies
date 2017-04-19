package converters;

import domain.Event;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Created by daviddelatorre on 19/4/17.
 */
@Component
@Transactional
public class EventToStringConverter implements Converter<Event, String> {

    @Override
    public String convert(Event actor) {
        Assert.notNull(actor);
        String result;
        result = String.valueOf(actor.getId());
        return result;
    }
}
