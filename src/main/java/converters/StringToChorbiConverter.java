package converters;

import domain.Actor;
import domain.Chorbi;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.ActorRepository;
import repositories.ChorbiRepository;

/**
 * Created by daviddelatorre on 29/3/17.
 */@Component
@Transactional
public class StringToChorbiConverter implements Converter<String, Chorbi> {

    @Autowired
    ChorbiRepository chorbiRepository;

    @Override
    public Chorbi convert(String text) {
        Chorbi result;
        int id;

        try {
            if (StringUtils.isEmpty(text))
                result = null;
            else {
                id = Integer.valueOf(text);
                result = chorbiRepository.findOne(id);
            }
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;

    }
}
