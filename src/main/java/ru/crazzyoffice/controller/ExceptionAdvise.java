package ru.crazzyoffice.controller;



import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.crazzyoffice.error.ErrorResponse;
import ru.crazzyoffice.error.ErrorType;
import ru.crazzyoffice.error.IllegalRequestDataException;
import ru.crazzyoffice.error.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;


import static ru.crazzyoffice.error.ErrorType.*;

@RestControllerAdvice
public class ExceptionAdvise {


    @ExceptionHandler({NotFoundException.class })
    @ResponseStatus(value = HttpStatus.NOT_FOUND )
    public ErrorResponse handleError(HttpServletRequest req, NotFoundException e) {
        return   logAndGetErrorInfo(req, e, false, DATA_NOT_FOUND);
    }


    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse illegalRequestDataError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, false, VALIDATION_ERROR);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalRequestDataException.class)
    public ErrorResponse conflict(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, false, DATA_ERROR);
    }

/*

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NotFoundException.class, MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    public ErrorResponse illegalRequestDataError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, false, VALIDATION_ERROR);
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse validationError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, false, VALIDATION_ERROR);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true, APP_ERROR);
    }*/




    private static ErrorResponse logAndGetErrorInfo(HttpServletRequest req, Exception e, boolean logException, ErrorType errorType) {
        Throwable rootCause = getRootCause(e);
      /*  if (logException) {
            log.error(errorType + " at request " + req.getRequestURL(), rootCause);
        } else {
            log.warn("{} at request  {}: {}", errorType, req.getRequestURL(), rootCause.toString());
        }*/
        return new ErrorResponse(req.getRequestURL(), errorType, rootCause.toString());
    }

    private static Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;
        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }



}