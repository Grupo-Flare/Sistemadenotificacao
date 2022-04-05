package br.com.flare.service;

import br.com.flare.model.Note;
import br.com.flare.model.Subscription;
import br.com.flare.repository.SubscriptionRepository;
import com.google.firebase.ErrorCode;
import com.google.firebase.FirebaseException;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private FirebaseMessaging firebaseMessaging;

    @Transactional
    public void saveSubscription(Subscription pushSubscription) {

        try {
            // Envia uma notificação de confirmação
            sendNotificationToOneUser(
                    new Note("Confirmando", "Ola, estamos te enviando essa notificação para confirmar que esta cadastrado em nosso app", ""),
                    pushSubscription);
            subscriptionRepository.save(pushSubscription);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
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
