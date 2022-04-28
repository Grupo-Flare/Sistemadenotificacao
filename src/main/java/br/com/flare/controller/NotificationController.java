package br.com.flare.controller;

import java.util.List;

import br.com.flare.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.flare.dto.NotificationDTO;
import br.com.flare.model.Note;
import br.com.flare.model.Subscription;
import br.com.flare.repository.SubscriptionRepository;
import br.com.flare.service.NotificationSenderService;

import javax.validation.Valid;

@Controller
@RequestMapping("/notification")
public class NotificationController {
    
    private final NotificationSenderService notificationSenderService;
    private final SubscriptionRepository subscriptionRepository;
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationController(NotificationSenderService notificationSenderService, SubscriptionRepository subscriptionRepository, NotificationRepository notificationRepository) {
        this.notificationSenderService = notificationSenderService;
        this.subscriptionRepository = subscriptionRepository;
        this.notificationRepository = notificationRepository;
    }
    
    @GetMapping
    public String form(NotificationDTO notificationDTO) {
        return "envia-notificacao";
    }
    
    @PostMapping("/send")
    public String send(@Valid NotificationDTO notificationDTO, BindingResult result) {
       
        if (result.hasErrors()) {
            return "envia-notificacao";
        }
        
        Note note = notificationDTO.toModel();
        List<Subscription> subscriptions = subscriptionRepository.findAll(); 
        notificationSenderService.sendNotificationToAllUsers(note, subscriptions);
        notificationRepository.save(note);
        return "redirect:/notification";
        
    }

    
}

