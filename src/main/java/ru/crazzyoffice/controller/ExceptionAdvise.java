package ru.crazzyoffice.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.crazzyoffice.error.ErrorType;
import ru.crazzyoffice.error.IllegalRequestDataException;
import ru.crazzyoffice.error.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import static ru.crazzyoffice.error.ErrorType.*;


/*
Global error handler , examples of error, default handler
 */

@ControllerAdvice
public class ExceptionAdvise {

    private static final Logger logger =
            LoggerFactory.getLogger(ExceptionAdvise.class);

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




    /*
     At first Error logged
     Then creating response. I didn't create model error on error.jsp , so if page doesn't exist

     */

    private static ModelAndView logAndGetErrorInfo(HttpServletRequest req, Exception e, boolean logException, ErrorType errorType) {
        Throwable rootCause = getRootCause(e);

        logger.error("Error   :  {}", "URL - " + req.getRequestURL() + ", ErrorType -" + errorType +
                ", Case - " + rootCause + ", Exception - " + e.toString() + ":" + e.getMessage());

        ModelAndView modelAndView = new ModelAndView("error");
        // ErrorResponse errorResponse = new  ErrorResponse(req.getRequestURL(), errorType, rootCause.toString());
        /*
        if page not found then  display 404 error , others error identified like  internals error 500
         */

        modelAndView.addObject("not_found", errorType.equals(PAGE_NOT_FOUND));
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