package sopt.study.testcode.jaemin.spring.api;

import java.net.BindException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public ApiResponse<Object> bindException(BindException e) {
		return ApiResponse.of(HttpStatus.BAD_REQUEST, e.getMessage(), null);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ApiResponse<Object> handleValidationException(MethodArgumentNotValidException e) {
		String message = e.getBindingResult().getFieldError().getDefaultMessage(); // 첫 번째 에러 메시지만
		return ApiResponse.of(HttpStatus.BAD_REQUEST, message);
	}
}
