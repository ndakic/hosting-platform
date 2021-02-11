package uns.ac.rs.hostplatserver.common;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Component
public class TimeProvider implements Serializable {

    

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Date now() {
        return new Date();
    }
	
    public Timestamp nowTimestamp() {
        return new Timestamp(new Date().getTime());
    }
}
