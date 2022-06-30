package br.com.flare.controller;

import br.com.flare.dto.CategoryDTO;
import br.com.flare.model.Category;
import br.com.flare.repository.ChannelRepository;
import br.com.flare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/canais")
public class ChannelController {

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String canais(Model model) {
        List<Category> channelsUser = channelRepository.findAllByUserRegistred("Iago Guilherme Ian Sales");
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
    public void cadastrar(@RequestBody String channel) {

        System.out.println(channel);

    }

}
