package com.springboot.boxview;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {
    
    public String getErrorPath() {
        return "/error";
    }

    @GetMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        System.out.println("Error with status code " + status + " happened. Support! Do something about it!");
        return new ModelAndView("error");
    }
}
