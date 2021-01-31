package uns.ac.rs.hostplatserver.util;

import lombok.experimental.UtilityClass;
import uns.ac.rs.hostplatserver.constant.DateTimeConstant;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

@UtilityClass
public class DateUtil {

    public Timestamp nowSystemTime() {
        return Timestamp.valueOf(ZonedDateTime.now(DateTimeConstant.SYSTEM_TIMEZONE).toLocalDateTime());
    }
}
