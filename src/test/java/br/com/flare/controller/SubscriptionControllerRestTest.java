package br.com.flare.controller;

import br.com.flare.dto.SubscriptionDTO;
import br.com.flare.exceptionHandler.ApiErrorException;
import br.com.flare.exceptionHandler.ApiErrorsOutput;
import br.com.flare.model.Subscription;
import br.com.flare.model.User;
import br.com.flare.repository.SubscriptionRepository;
import br.com.flare.service.SubscriptionService;
import br.com.flare.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class SubscriptionControllerRestTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @MockBean
    private SubscriptionService subscriptionService;

    private SubscriptionDTO subscriptionDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        subscriptionDTO = new SubscriptionDTO();
    }

    @Test
    void deveRetornarBadRequestParaCampoVazio() throws Exception {

        subscriptionDTO.setToken("");

        ApiErrorsOutput apiErrorsOutput = new ApiErrorsOutput();
        apiErrorsOutput.addFieldError("token", "must not be blank");

        mockMvc
                .perform(MockMvcRequestBuilders.post("/subscription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(subscriptionDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(JsonUtils.toJson(apiErrorsOutput)));
    }

    @Test
    void deveRetornarOk() throws Exception {
        subscriptionDTO.setToken(UUID.randomUUID().toString());

        doNothing()
                .when(subscriptionService)
                .saveSubscription(subscriptionDTO.toModel(new User("", "")));

        mockMvc
                .perform(MockMvcRequestBuilders.post("/subscription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(subscriptionDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void deveRetornarBadRequestParaTokenInvalido() throws Exception {
        subscriptionDTO.setToken(UUID.randomUUID().toString());

        ApiErrorsOutput apiErrorsOutput = new ApiErrorsOutput();
        apiErrorsOutput.addError("Erro! O token é invalido");

        doThrow(new ApiErrorException(HttpStatus.BAD_REQUEST, "Erro! O token é invalido"))
                .when(subscriptionService)
                .saveSubscription(any(Subscription.class));

        mockMvc
                .perform(MockMvcRequestBuilders.post("/subscription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(subscriptionDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(JsonUtils.toJson(apiErrorsOutput)));

    }

    @Test
    void deveRetornarUnprocessableEntityParaTokenJaRegistrado() throws Exception {
        subscriptionDTO.setToken(UUID.randomUUID().toString());

        subscriptionRepository.save(subscriptionDTO.toModel(new User("", "")));

        ApiErrorsOutput apiErrorsOutput = new ApiErrorsOutput();
        apiErrorsOutput.addError("O objeto já existe na base de dados");

        mockMvc
                .perform(MockMvcRequestBuilders.post("/subscription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(subscriptionDTO)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json(JsonUtils.toJson(apiErrorsOutput)));
    }

   /*
        ESSE TESTE UTILIZA O BANCO DE DADOS DE VERDADE,
        DEIXE O BANCO EXECUTANDO COM DADOS JÁ INSERIDOS
    */
    @Test
    void naoDeveSalvarNoBancoPushSeUsuarioNaoEncontrado() throws Exception {

        subscriptionDTO.setToken("cdSrO1-rCBFBT1IV5UlL6D:APA91bGG1WnVutYFIl_WkWc2wkEVGM0l1VaMl0QNTZPSd1Uv87Kn3jweT2_JLwvMvKpLzKOqOxpAegPlbxPaYXbSxGx01WhHQ_uFqQde1vchRUdPQlUJ6ZWCDVcSrxLRJMvkeDwvqui1");
        subscriptionDTO.setUser("randomName");

        mockMvc
                .perform(MockMvcRequestBuilders.post("/subscription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(subscriptionDTO)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Usuario nao encontrado"));

    }

}