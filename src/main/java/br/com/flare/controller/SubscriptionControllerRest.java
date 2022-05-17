package br.com.flare.controller;

import br.com.flare.dto.SubscriptionDTO;
import br.com.flare.model.User;
import br.com.flare.repository.UserRepository;
import br.com.flare.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/subscription")
public class SubscriptionControllerRest {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> subscribe(@RequestBody @Valid SubscriptionDTO subscriptionDTO) {

        Optional<User> user = userRepository.findByName(subscriptionDTO.getUser());

        if (user.isPresent())
            subscriptionService.saveSubscription(subscriptionDTO.toModel(user.get()));
        else
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");

        return ResponseEntity.ok().build();

    }

}
