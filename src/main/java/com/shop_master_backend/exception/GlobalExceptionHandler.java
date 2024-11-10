package com.shop_master_backend.exception;

import com.shop_master_backend.exception.runtime.ProductNotFoundException;
import com.shop_master_backend.exception.runtime.RoleNotFoundException;
import com.shop_master_backend.exception.runtime.UserNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredentialsException(BadCredentialsException ex) {
        String message = (ex.getMessage() == null || ex.getMessage().isEmpty())
                ? "The username or password is incorrect"
                : ex.getMessage();
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, message);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDeniedException(AccessDeniedException ex) {
        String message = (ex.getMessage() == null || ex.getMessage().isEmpty())
                ? "You are not authorized to access this resource"
                : ex.getMessage();
        return ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, message);
    }

    @ExceptionHandler(SignatureException.class)
    public ProblemDetail handleSignatureException(SignatureException ex) {
        String message = (ex.getMessage() == null || ex.getMessage().isEmpty())
                ? "The JWT signature is invalid"
                : ex.getMessage();
        return ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, message);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ProblemDetail handleExpiredJwtException(ExpiredJwtException ex) {
        String message = (ex.getMessage() == null || ex.getMessage().isEmpty())
                ? "The JWT token has expired"
                : ex.getMessage();
        return ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, message);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFoundException(UserNotFoundException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ProblemDetail handleRoleNotFoundException(RoleNotFoundException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ProblemDetail handleRoleNotFoundException(ProductNotFoundException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneralException(Exception ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown internal server error.");
    }

}
