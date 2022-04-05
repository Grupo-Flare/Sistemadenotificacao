package br.com.flare.service;

import br.com.flare.model.Note;
import br.com.flare.model.Subscription;
import br.com.flare.repository.SubscriptionRepository;
import br.com.flare.vallidation.ApiErrorException;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private FirebaseMessaging firebaseMessaging;

    @Transactional
    public void saveSubscription(Subscription pushSubscription) {

        try {
            subscriptionRepository.save(pushSubscription);
            // Envia uma notificação de confirmação
            sendNotificationToOneUser(
                    new Note("Confirmando", "Ola, estamos te enviando essa notificação para confirmar que esta cadastrado em nosso app", ""),
                    pushSubscription);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            throw new ApiErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage() + ": " + e.getMessagingErrorCode());
        }

    }

    public String sendNotificationToOneUser(Note note, Subscription subscription) throws FirebaseMessagingException {

        Notification notification = Notification
                .builder()
                .setTitle(note.getTitle())
                .setBody(note.getMessage())
                .setImage(note.getImageUrl())
                .build();

        Message message = Message
                .builder()
                .setToken(subscription.getToken())
                .setNotification(notification)
                .build();

        return firebaseMessaging.send(message);

    }

}
