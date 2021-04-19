package ru.crazzyoffice.controller;



import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.crazzyoffice.error.ErrorResponse;
import ru.crazzyoffice.error.ErrorType;
import ru.crazzyoffice.error.IllegalRequestDataException;
import ru.crazzyoffice.error.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;


import static ru.crazzyoffice.error.ErrorType.*;

@ControllerAdvice
public class ExceptionAdvise {


    @ExceptionHandler({NotFoundException.class })
    @ResponseStatus(value = HttpStatus.NOT_FOUND )
    public ModelAndView handleError(HttpServletRequest req, NotFoundException e) {
        return   logAndGetErrorInfo(req, e, true, DATA_NOT_FOUND);
    }


    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView illegalRequestDataError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true, VALIDATION_ERROR);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalRequestDataException.class)
    public ModelAndView conflict(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true, DATA_ERROR);
    }


    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    public ModelAndView validationError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true, VALIDATION_ERROR);
    }



    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true, PAGE_NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView   defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;


        return logAndGetErrorInfo(req, e, true, APP_ERROR);
    }




    private static ModelAndView logAndGetErrorInfo(HttpServletRequest req, Exception e, boolean logException, ErrorType errorType) {
        Throwable rootCause = getRootCause(e);

        //log.error(errorType + " at request " + req.getRequestURL(), rootCause);

        System.out.println("URL - "+req.getRequestURL());
        System.out.println("ErrorTYpe -"+errorType);
        System.out.println("Case - " + rootCause);
        System.out.println("Exception - " + e.toString());

        ModelAndView modelAndView = new ModelAndView("error");
        ErrorResponse errorResponse = new  ErrorResponse(req.getRequestURL(), errorType, rootCause.toString());

        modelAndView.addObject("not_found",errorType.equals(PAGE_NOT_FOUND) ? true : false);
        return modelAndView;
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