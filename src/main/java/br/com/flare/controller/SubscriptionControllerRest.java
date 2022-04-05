package br.com.flare.controller;

import br.com.flare.model.Subscription;
import br.com.flare.repository.SubscriptionRepository;
import br.com.flare.service.SubscriptionService;
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
    SubscriptionService subscriptionService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> subscribe(@RequestBody @Valid SubscriptionDTO subscriptionDTO) {

        subscriptionService.saveSubscription(subscriptionDTO.toModel());

        return ResponseEntity.ok().build();

    }

}
