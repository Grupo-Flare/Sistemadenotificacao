package br.com.flare.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/home")
public class HomeController {

  @GetMapping
  public String home(Model model, OAuth2AuthenticationToken authentication, Principal principal) {
    model.addAttribute("name", principal.getName());
    return "bem-vindo";
  }

}
