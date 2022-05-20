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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
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

        Optional<User> userByEmail = userRepository.findByEmail(userDTO.getEmail());
        if (userByEmail.isPresent()) {
            model.addAttribute("emailerror", "Este email já está sendo usado");
            return "gerenciarUsuarios";
        }

        User user = userDTO.toModel();
        // Busca as categorias a serem permitidas no banco
        List<Category> findAllByName = categoryRepository.findAllByName(userDTO.getPermissao());
        user.setAllowToSendNotification(findAllByName);

        userRepository.save(user);

        return "gerenciarUsuarios";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam String name, UserDTO userDTO, Model model) {

        List<Category> categories = categoryRepository.findAllByOrderByNameAsc();
        model.addAttribute("categorias", categories);

        if (name.isBlank()) {
            return "gerenciarUsuarios";
        }

        Optional<User> user = userRepository.findByName(name);
        if (user.isEmpty()) {
            model.addAttribute("nameDerror", "Usuario não encontrado!!!");
            return "gerenciarUsuarios";
        }

        userRepository.delete(user.get());

        return "redirect:/user";
    }

    @PostMapping("/update")
    public String update(@Valid UserDTO userDTO, BindingResult result, Model model) {

        List<Category> categories = categoryRepository.findAllByOrderByNameAsc();
        model.addAttribute("categorias", categories);

        if (result.hasErrors())
            return "gerenciarUsuarios";

        User user = userDTO.toModel();

        Optional<User> byName = userRepository.findByNameAndEmail(user.getName(), userDTO.getEmail());
        if (byName.isEmpty()) {
            model.addAttribute("nameUerror", "Usuario não encontrado");
            return "gerenciarUsuarios";
        }

        List<Category> findAllByName = categoryRepository.findAllByName(userDTO.getPermissao());
        byName.get().getAllowToSendNotification().addAll(findAllByName);

        userRepository.save(byName.get());

        return "redirect:/user";

    }

}
