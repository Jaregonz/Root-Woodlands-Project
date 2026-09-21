package com.reactorsolutions.woodland.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<ProblemDetail> handleNotFound(NoSuchElementException exception,
                                                        HttpServletRequest request) {
        return response(HttpStatus.NOT_FOUND, "Recurso no encontrado", exception.getMessage(), request, "not-found");
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<ProblemDetail> handleVersionConflict(OptimisticLockingFailureException exception,
                                                               HttpServletRequest request) {
        return response(HttpStatus.CONFLICT, "Conflicto de versión", exception.getMessage(), request, "version-conflict");
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ProblemDetail> handleInvalidState(IllegalStateException exception,
                                                            HttpServletRequest request) {
        return response(HttpStatus.CONFLICT, "Estado no válido", exception.getMessage(), request, "invalid-state");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> handleIllegalArgument(IllegalArgumentException exception,
                                                               HttpServletRequest request) {
        return response(HttpStatus.BAD_REQUEST, "Solicitud no válida", exception.getMessage(), request, "invalid-request");
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ProblemDetail> handleResponseStatus(ResponseStatusException exception,
                                                              HttpServletRequest request) {
        HttpStatus status = HttpStatus.valueOf(exception.getStatusCode().value());
        String detail = exception.getReason() == null ? status.getReasonPhrase() : exception.getReason();
        return response(status, status.getReasonPhrase(), detail, request, "request-error");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidation(MethodArgumentNotValidException exception,
                                                          HttpServletRequest request) {
        Map<String, String> errors = new LinkedHashMap<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.putIfAbsent(error.getField(), error.getDefaultMessage());
        }
        ProblemDetail problem = problem(HttpStatus.BAD_REQUEST, "Datos no válidos",
                "Uno o más campos no superan la validación.", request, "validation-error");
        problem.setProperty("errors", errors);
        return ResponseEntity.badRequest().body(problem);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class,
            HttpMessageNotReadableException.class})
    public ResponseEntity<ProblemDetail> handleMalformedRequest(Exception exception, HttpServletRequest request) {
        return response(HttpStatus.BAD_REQUEST, "Solicitud no válida",
                "Comprueba los parámetros y el cuerpo de la petición.", request, "malformed-request");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleUnexpected(Exception exception, HttpServletRequest request) {
        LOGGER.error("Error no controlado al procesar {} {}", request.getMethod(), request.getRequestURI(), exception);
        return response(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno",
                "Se ha producido un error inesperado.", request, "internal-error");
    }

    private ResponseEntity<ProblemDetail> response(HttpStatus status, String title, String detail,
                                                   HttpServletRequest request, String code) {
        return ResponseEntity.status(status).body(problem(status, title, detail, request, code));
    }

    private ProblemDetail problem(HttpStatus status, String title, String detail,
                                  HttpServletRequest request, String code) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setTitle(title);
        problem.setType(URI.create("https://woodland.reactorsolutions.com/problems/" + code));
        problem.setInstance(URI.create(request.getRequestURI()));
        return problem;
    }
}
