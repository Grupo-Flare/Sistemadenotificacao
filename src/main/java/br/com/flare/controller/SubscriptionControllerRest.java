package br.com.flare.controller;

import br.com.flare.model.Subscription;
import br.com.flare.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.flare.dto.SubscriptionDTO;

@RestController
@RequestMapping("/subscription")
public class SubscriptionControllerRest {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> subscribe(@RequestBody @Valid SubscriptionDTO subscriptionDTO) {

        Subscription subscription = subscriptionDTO.toModel();
        subscriptionRepository.save(subscription); // Salva a subscription no banco de dados

        return ResponseEntity.ok().build();

    }

}
