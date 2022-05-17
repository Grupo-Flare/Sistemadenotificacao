package br.com.flare.service;

import br.com.flare.exceptionHandler.ApiErrorException;
import br.com.flare.model.Note;
import br.com.flare.model.Subscription;
import br.com.flare.model.User;
import br.com.flare.repository.SubscriptionRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class SubscriptionServiceTest {

    @Mock
    private FirebaseMessaging firebaseMessagingMock = mock(FirebaseMessaging.class);

    @Mock
    private SubscriptionRepository subscriptionRepositoryMock = mock(SubscriptionRepository.class);

    @Mock
    private NotificationSenderService notificationSenderService = mock(NotificationSenderService.class);

    @InjectMocks
    private SubscriptionService subscriptionService;

    @BeforeEach
    public void setup() {
        subscriptionService = new SubscriptionService(subscriptionRepositoryMock, notificationSenderService);
    }

    @Test
    void deveEstourarApiErrorExeceptionParaFirebaseMessaging() throws FirebaseMessagingException {

        when(subscriptionRepositoryMock.save(any(Subscription.class)))
                .thenReturn(new Subscription("TOKEN",new User("","")));

        doThrow(FirebaseMessagingException.class)
                .when(notificationSenderService)
                .sendNotificationToOneUser(any(Note.class), any(Subscription.class));

        ApiErrorException token = assertThrows(ApiErrorException.class, () -> {
            subscriptionService.saveSubscription(new Subscription("TOKEN", new User("","")));
        });

        assertEquals(HttpStatus.BAD_REQUEST, token.getHttpStatus());

    }

    @Test
    void deveEstourarApiErrorExceptionParaPersistenceException() {

        when(subscriptionRepositoryMock.save(any(Subscription.class)))
                .thenThrow(new PersistenceException("Erro ao persistir na base de dados"));

        ApiErrorException token = assertThrows(ApiErrorException.class, () -> {
            subscriptionService.saveSubscription(new Subscription("TOKEN", new User("","")));
        });

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, token.getHttpStatus());

        assertEquals("Erro ao persistir na base de dados", token.getReason());

    }

}