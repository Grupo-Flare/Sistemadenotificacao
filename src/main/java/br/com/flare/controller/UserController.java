package br.com.flare.controller;

import br.com.flare.dto.UserDTO;
import br.com.flare.model.Category;
import br.com.flare.model.User;
import br.com.flare.repository.CategoryRepository;
import br.com.flare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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
    public String getPage(UserDTO userDTO, Model model) {

        List<Category> categories = categoryRepository.findAllByOrderByNameAsc();
        model.addAttribute("categorias", categories);

        return "gerenciarUsuarios";
    }

    @PostMapping
    public String register(@Valid UserDTO userDTO, BindingResult result, Model model) {

        List<Category> categories = categoryRepository.findAllByOrderByNameAsc();
        model.addAttribute("categorias", categories);

        if (result.hasErrors())
            return "gerenciarUsuarios";

        System.out.println(userDTO.getPermissao());
        User user = userDTO.toModel();

        userRepository.save(user);

        return "redirect:/user";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam String name, UserDTO userDTO, Model model) {

        List<Category> categories = categoryRepository.findAllByOrderByNameAsc();
        model.addAttribute("categorias", categories);

        if (name.isBlank()) {
            return "gerenciarUsuarios";
        }

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
