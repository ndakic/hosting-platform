package uns.ac.rs.hostplatserver.constant;

import lombok.Getter;

@Getter
public enum Md5Salt {

    USER("HostplatUserSalt22"),
    LABEl("HostplatLabelSalt141");

    private final String salt;

    Md5Salt(String salt) {
        this.salt = salt;
    }
}
