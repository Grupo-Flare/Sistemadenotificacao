package br.com.flare.controller;

import br.com.flare.dto.CategoryDTO;
import br.com.flare.dto.ChannelDTO;
import br.com.flare.model.Category;
import br.com.flare.model.User;
import br.com.flare.repository.ChannelRepository;
import br.com.flare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/canais")
public class ChannelController {

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String canais(Model model, ChannelDTO channelDTO, Principal principal) {
        List<Category> channelsUser = channelRepository.findAllByUserRegistred(principal.getName());
        List<Category> channels = channelRepository.findAllByOrderByNameAsc();

        List<CategoryDTO> dtos = channels.stream().map(CategoryDTO::new).collect(Collectors.toList());

        dtos.forEach(categoryDTO -> {
            boolean match = channelsUser.stream()
                    .anyMatch(channel -> channel.getName().equals(categoryDTO.getName()));
            if (match)
                categoryDTO.setIsRegistred(true);
        });

        model.addAttribute("channel", dtos);
        return "canaisComunicacao";
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody ChannelDTO channel, Principal principal) {

        Optional<User> optionalUser = userRepository.findByName(principal.getName());
        Optional<Category> category = channelRepository.findByName(channel.getName());
        if (category.isEmpty())
            return ResponseEntity.notFound().build();

        if (optionalUser.isEmpty())
            return ResponseEntity.notFound().build();

        User user = optionalUser.get();
        user.addInscription(category.get());

        userRepository.save(user);

        return ResponseEntity.ok().body(channel.getName());

    }

    @DeleteMapping
    public ResponseEntity<?> descadastrar(@RequestBody ChannelDTO channel, Principal principal) {

        Optional<User> optionalUser = userRepository.findByName(principal.getName());
        Optional<Category> category = channelRepository.findByName(channel.getName());
        if (category.isEmpty())
            return ResponseEntity.notFound().build();

        if (optionalUser.isEmpty())
            return ResponseEntity.notFound().build();

        User user = optionalUser.get();
        user.removeInscription(category.get());

        userRepository.save(user);

        return ResponseEntity.ok().build();

    }

}
