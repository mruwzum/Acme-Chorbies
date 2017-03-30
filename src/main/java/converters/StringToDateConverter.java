package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * Created by daviddelatorre on 22/12/16.
 */
public class StringToDateConverter implements Converter<String, Date> {


    @SuppressWarnings("deprecation")
	@Override
    public Date convert(String source) {
        Date result;
        try {
            if (StringUtils.isEmpty(source))
                result = null;
            else {
                result=new Date(source);
            }
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }
        return result;
    }
}
