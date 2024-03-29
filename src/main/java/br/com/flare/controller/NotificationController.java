package br.com.flare.controller;

import br.com.flare.dto.NotificationDTO;
import br.com.flare.exceptionHandler.MvcErrorException;
import br.com.flare.model.Category;
import br.com.flare.model.Note;
import br.com.flare.repository.ChannelRepository;
import br.com.flare.repository.NotificationRepository;
import br.com.flare.service.NotificationSenderService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.PersistenceException;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationSenderService notificationSenderService;
    private final NotificationRepository notificationRepository;
    private final ChannelRepository channelRepository;

    @Autowired
    public NotificationController(NotificationSenderService notificationSenderService, NotificationRepository notificationRepository, ChannelRepository channelRepository) {
        this.notificationSenderService = notificationSenderService;
        this.notificationRepository = notificationRepository;
        this.channelRepository = channelRepository;
    }

    @GetMapping
    public String form(NotificationDTO notificationDTO, Model model) {

        List<Category> categories = channelRepository.findAllByOrderByNameAsc();
        model.addAttribute("categorias", categories);

        return "envia-notificacao";
    }

    @PostMapping
    public String send(@Valid NotificationDTO notificationDTO, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "envia-notificacao";
        }

        /*
            TODO:
             Validar se foi enviado a categoria, se não, enviar para todos
             Implementar a logica de envio por categoria
        */
        Note note = notificationDTO.toModel();
        try {
            if (notificationDTO.getDate().isEmpty() || notificationDTO.getTime().isEmpty()) {
                notificationSenderService.sendNotificationToAllUsers(note);
            } else {
                notificationSenderService.scheduleNotificationSending(note);
            }
            notificationRepository.save(note);
        } catch (FirebaseMessagingException e) {
            throw new MvcErrorException(HttpStatus.BAD_REQUEST, e.getMessage() + ": " + e.getMessagingErrorCode());
        } catch (PersistenceException e) {
            throw new MvcErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage() + "\n" + e.getCause());
        } catch (SchedulerException e) {
            throw new MvcErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro no agendamento " + e);
        }

        return "redirect:/notification";

    }

    @GetMapping("/historic")
    public String historic(@RequestParam(required = false) String category, Model model) {

        List<Note> notifications;

        if (category == null)
            notifications = notificationRepository.findAll();
        else
            notifications = notificationRepository.findByCategory(category);

        if (notifications.isEmpty())
            model.addAttribute("semNotificacoes", "Não ha notificações");
        else
            model.addAttribute("notificacoes", notifications);

        // TODO: Filtar somente pelas categorias em que o usuario esta cadastrado
        List<Category> categories = channelRepository.findAllByOrderByNameAsc();
        model.addAttribute("categorias", categories);

        return "historicoNotificacao";
    }

}

