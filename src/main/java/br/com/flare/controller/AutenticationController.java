package br.com.flare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AutenticationController {

    @GetMapping("/login")
    public String login(){
        return "Login";
    }

}
