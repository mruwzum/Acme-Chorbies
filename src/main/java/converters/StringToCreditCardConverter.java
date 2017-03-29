package converters;

import domain.Actor;
import domain.CreditCard;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.ActorRepository;
import repositories.CreditCardRepository;

/**
 * Created by daviddelatorre on 29/3/17.
 */
@Component
@Transactional
public class StringToCreditCardConverter implements Converter<String, CreditCard> {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Override
    public CreditCard convert(String text) {
        CreditCard result;
        int id;

        try {
            if (StringUtils.isEmpty(text))
                result = null;
            else {
                id = Integer.valueOf(text);
                result = creditCardRepository.findOne(id);
            }
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;

    }
}
