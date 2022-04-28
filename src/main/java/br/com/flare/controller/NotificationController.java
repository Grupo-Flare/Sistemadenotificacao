package br.com.flare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.flare.dto.NotificationDTO;
import br.com.flare.model.Note;
import br.com.flare.model.Subscription;
import br.com.flare.repository.SubscriptionRepository;
import br.com.flare.service.SendNotificationService;

@Controller
@RestController("/notification")
public class NotificationController {
    
    private SendNotificationService sendNotificationService;
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    public NotificationController(SendNotificationService sendNotificationService, SubscriptionRepository subscriptionRepository) {
        this.sendNotificationService = sendNotificationService;
        this.subscriptionRepository = subscriptionRepository;
    }
    
    @GetMapping
    public String form(NotificationDTO notificationDTO) {
        return "envia-notificacao";
    }
    
    @PostMapping("/send")
    public String send(NotificationDTO notificationDTO, BindingResult result) {
       
        if (result.hasErrors()) {
            System.out.println("Erro");
        }
        
        Note note = notificationDTO.toModel();
        List<Subscription> subscriptions = subscriptionRepository.findAll(); 
        sendNotificationService.sendNotificationToAllUsers(note, subscriptions);
        return "envia-notificacao"; 
        
    }

    
}

