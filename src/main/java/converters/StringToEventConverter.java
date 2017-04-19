package converters;

import domain.Event;
import domain.Liked;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.EventRespository;
import repositories.LikedRepository;

/**
 * Created by daviddelatorre on 19/4/17.
 */
@Component
@Transactional
public class StringToEventConverter implements Converter<String, Event> {

    @Autowired
    EventRespository eventRespository;

    @Override
    public Event convert(String text) {
        Event result;
        int id;

        try {
            if (StringUtils.isEmpty(text))
                result = null;
            else {
                id = Integer.valueOf(text);
                result = eventRespository.findOne(id);
            }
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;

    }
}
