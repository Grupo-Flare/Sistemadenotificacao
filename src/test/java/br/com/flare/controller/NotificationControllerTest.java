package br.com.flare.controller;

import br.com.flare.dto.NotificationDTO;
import br.com.flare.model.Note;
import br.com.flare.repository.NotificationRepository;
import br.com.flare.repository.SubscriptionRepository;
import br.com.flare.service.NotificationSenderService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.PersistenceException;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NotificationController.class)
public class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private NotificationSenderService notificationSenderService;

    @MockBean
    private SubscriptionRepository subscriptionRepository;

    @MockBean
    private NotificationRepository notificationRepository;

    private NotificationDTO notificationDTO;

    @Test
    public void deveRetornarMensagemDeErroParaCampoInvalido() throws Exception {

        mockMvc.perform(post("/notification/send")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "")
                        .param("message", ""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("O campo titulo e obrigatorio")))
                .andExpect(content().string(containsString("O campo mensagem e obrigatorio")));

    }

    @Test
    public void deveRetornarPaginaDeErroParaFirebaseMessagingException() throws Exception {

        notificationDTO = new NotificationDTO("Titulo", "Mensagem");

        doThrow(FirebaseMessagingException.class)
                .when(notificationSenderService)
                .sendNotificationToAllUsers(any(Note.class), anyList());

        this.mockMvc.perform(post("/notification/send")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "Teste")
                        .param("message", "teste"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("400 BAD_REQUEST")));
    }

    @Test
    public void deveRetornarPaginaDeErroParaPercistenceException() throws Exception {

        notificationDTO = new NotificationDTO("Titulo", "Mensagem");

        doThrow(new PersistenceException("Erro ao buscar no banco"))
                .when(subscriptionRepository)
                .findAll();

        this.mockMvc.perform(post("/notification/send")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "Teste")
                        .param("message", "teste"))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("500 INTERNAL_SERVER_ERROR")))
                .andExpect(content().string(containsString("Erro ao buscar no banco")));

    }

}