package converters;

import domain.Actor;
import domain.Coordinate;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.ActorRepository;
import repositories.CoordinateRepository;

/**
 * Created by daviddelatorre on 29/3/17.
 */
@Component
@Transactional
public class StringToCoordinateConverter implements Converter<String, Coordinate> {

    @Autowired
    CoordinateRepository coordinateRepository;

    @Override
    public Coordinate convert(String text) {
        Coordinate result;
        int id;

        try {
            if (StringUtils.isEmpty(text))
                result = null;
            else {
                id = Integer.valueOf(text);
                result = coordinateRepository.findOne(id);
            }
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;

    }


}
