package inhatc.group.mojuk.config.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.security.sasl.AuthenticationException;
import java.nio.file.AccessDeniedException;

//@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler_save {
	//slf4j 어노테이션이 존재하면 logger 선언 없이 사용 가능
	//private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * HTTP Status 400
	 */
	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<String> handleBadRequestException(final RuntimeException e) {
		log.error("handleBadRequestException: {}", e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * @ModelAttribute에서 커맨드 객체에 바인딩 되지 않는 경우 발생
	 */
	@ExceptionHandler(BindException.class)
	protected ResponseEntity<String> handleBindException(final BindException e) {
		log.error("handleBindException: {}", e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Enum Type이 일치하지 않아, 바인딩 되지 않는 경우 발생
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<String> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e) {
		log.error("handleMethodArgumentTypeMismatchException: {}", e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * @RequestBody로 전달받는 파라미터가 바인딩 되지 않는 경우 발생
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<String> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
		log.error("handleMethodArgumentNotValidException: {}", e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * HTTP Status 401
	 */
	@ExceptionHandler(AuthenticationException.class)
	protected ResponseEntity<Object> handleAuthenticationException(final AuthenticationException e) {
		log.error("handleAuthenticationException: {}", e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
	}

	/**
	 * HTTP Status 403
	 */
	@ExceptionHandler(AccessDeniedException.class)
	protected ResponseEntity<Object> handleAccessDeniedException(final AccessDeniedException e) {
		log.error("handleAccessDeniedException: {}", e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
	}

	/**
	 * 브라우저가 지원하지 않는 HTTP Request Method 요청(호출) 시 발생
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e) {
		log.error("handleHttpRequestMethodNotSupportedException: {}", e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
	}

	/**
	 * HTTP Status 500
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleException(final Exception e) {
		log.error("handleException: {}", e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
