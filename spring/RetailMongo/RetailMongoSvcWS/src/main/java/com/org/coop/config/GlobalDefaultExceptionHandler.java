/**
 * This class handles global exceptions. If any custom exception, HttpUnauthorizedException occurs then it returns unauthorized response
 * For AccessDeniedException, it redirects to the access denied page else it will redirect to the error page
 */
package com.org.coop.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.coop.org.exception.HttpUnauthorizedException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	private static final Logger log = Logger.getLogger(GlobalDefaultExceptionHandler.class); 
    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
    public Map<String, String> exceptionInProcessing(Exception e) {
        Map<String, String> exception = new HashMap<String, String>();

        log.error("Unable to process. Some error occured: " + e.getMessage(), e);
        exception.put("code", "417");
        exception.put("reason", e.getMessage());

        return exception;
    }
    
    @ExceptionHandler({HttpUnauthorizedException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Map<String, String> unauthorizedAccess(Exception e) {
        Map<String, String> exception = new HashMap<String, String>();

        log.error("unauthorized Access to the API: " + e.getMessage(), e);
        exception.put("code", "401");
        exception.put("reason", e.getMessage());

        return exception;
    }
}
