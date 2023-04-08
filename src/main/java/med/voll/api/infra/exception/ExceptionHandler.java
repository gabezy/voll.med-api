package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleError404NotFound() {
        return ResponseEntity.notFound().build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleError400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ExceptionValidationDto::new).toList());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failure");
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
    }

    // Custom exceptions
    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidAppointmentDateException.class)
    public ResponseEntity<Object> handleInvalidAppointmentDateException() {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new InvalidAppointmentDateException().getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(PatientBookTwoAppointmentsInTheSameDayException.class)
    public ResponseEntity<Object> handlePatientBookTwoAppointmentsInTheSameDayException() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new PatientBookTwoAppointmentsInTheSameDayException().getMessage());
    }
    private record ExceptionValidationDto(String field, String message) {
        public ExceptionValidationDto(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
