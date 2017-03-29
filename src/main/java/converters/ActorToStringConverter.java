package converters;

import domain.Actor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Created by daviddelatorre on 29/3/17.
 */@Component
@Transactional
public class ActorToStringConverter implements Converter<Actor, String> {

    @Override
    public String convert(Actor actor) {
        Assert.notNull(actor);
        String result;
        result = String.valueOf(actor.getId());
        return result;
    }

}
