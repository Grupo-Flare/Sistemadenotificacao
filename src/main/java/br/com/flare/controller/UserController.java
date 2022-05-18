package br.com.flare.controller;

import br.com.flare.dto.UserDTO;
import br.com.flare.model.Category;
import br.com.flare.model.User;
import br.com.flare.repository.CategoryRepository;
import br.com.flare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String getPage(UserDTO userDTO) {
        return "gerenciarUsuarios";
    }

    @PostMapping
    public String register(@Valid UserDTO userDTO) {

        User user = userDTO.toModel();

        userRepository.save(user);

        return "redirect:/user";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam String name) {

        System.out.println("Nome: " + name);
        Optional<User> user = userRepository.findByName(name);

        if (user.isEmpty()) {
            System.out.println("Usuario não encontrado!!!");
            return "gerenciarUsuarios";
        }
        
        userRepository.delete(user.get());

        return "redirect:/user";
    }

}
