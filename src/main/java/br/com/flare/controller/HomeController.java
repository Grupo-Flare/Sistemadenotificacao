package br.com.flare.controller;

import br.com.flare.model.User;
import br.com.flare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/home")
public class HomeController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping
  // TODO: associar o email e nome de usuario na tabela de usuarios
  public String home(Model model, OAuth2AuthenticationToken principal2, Principal principal) {
    model.addAttribute("name", principal.getName());
//    userRepository.save(new User(principal.getName(), principal2.getPrincipal().getAttribute("preferred_username").toString()));
    System.out.println();
    return "bem-vindo";
  }

}
