package com.threemaster.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    public static final Logger logger = LoggerFactory.getLogger("global.logger");

    @ExceptionHandler(LoginRequiredException.class)
    public void handleAccessDeniedException(HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
        response.sendRedirect("/register");
    }

}