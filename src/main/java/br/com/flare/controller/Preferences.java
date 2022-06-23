package br.com.flare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/configuracoes")
public class Preferences {

    @GetMapping
    public String preferences() {
       return "preferencias-notificacao";
    }

}
