package uns.ac.rs.hostplatserver.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.codec.digest.DigestUtils;
import uns.ac.rs.hostplatserver.constant.Md5Salt;

@UtilityClass
public class Md5Generator {

    public static String generateHash(String value, Md5Salt salt) {
        return DigestUtils.md5Hex(value + salt.getSalt());
    }

    public static String generateHash(long value, Md5Salt salt) {
        return generateHash(Long.toString(value), salt);
    }
}
