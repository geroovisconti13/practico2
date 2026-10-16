package ar.com.practico2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class LoginController {

    //se copia y se pega
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String Index(Map<String, Object> model) {
        return "../login/login";
    }

}
