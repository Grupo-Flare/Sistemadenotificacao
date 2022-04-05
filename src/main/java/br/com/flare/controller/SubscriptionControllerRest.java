package br.com.flare.controller;

import br.com.flare.dto.SubscriptionDTO;
import br.com.flare.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/subscription")
public class SubscriptionControllerRest {

    @Autowired
    SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<?> subscribe(@RequestBody @Valid SubscriptionDTO subscriptionDTO) {

        subscriptionService.saveSubscription(subscriptionDTO.toModel());

        return ResponseEntity.ok().build();

    }

}
