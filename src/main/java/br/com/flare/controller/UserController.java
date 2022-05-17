package br.com.flare.controller;

import br.com.flare.dto.UserDTO;
import br.com.flare.model.Category;
import br.com.flare.model.User;
import br.com.flare.repository.CategoryRepository;
import br.com.flare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

   /*
   * Controller de teste
   * TODO: Deletar posteriormente
   * */

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody @Valid UserDTO userDTO) {

        List<Category> categoryAllByName = categoryRepository.findAllByName(userDTO.getCategories());

        if (categoryAllByName.isEmpty())
            return ResponseEntity.notFound().build();

        User user = userDTO.toModel(categoryAllByName);
        userRepository.save(user);

        return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        List<User> all = userRepository.findAll();
        return ResponseEntity.ok(all);
    }

}
