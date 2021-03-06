package uns.ac.rs.hostplatserver.exception;

/*https://www.mkyong.com/spring-boot/spring-rest-error-handling-example/
https://dzone.com/articles/spring-rest-service-exception-handling-1
https://www.baeldung.com/exception-handling-for-rest-with-spring
*/
public class BadRequestException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
        super(message);
    }
}