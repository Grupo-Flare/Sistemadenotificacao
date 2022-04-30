package br.com.flare.service;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import br.com.flare.exceptionHandler.ApiErrorException;
import br.com.flare.exceptionHandler.MvcErrorException;
import br.com.flare.model.Note;
import br.com.flare.model.Subscription;
import org.springframework.stereotype.Service;

@Service
public class NotificationSenderService {

    private final FirebaseMessaging firebaseMessaging;

    @Autowired
    public NotificationSenderService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    public void sendNotificationToOneUser(Note note, Subscription subscription) throws FirebaseMessagingException {

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

        firebaseMessaging.send(message);
    }

    public void sendNotificationToAllUsers(Note note, List<Subscription> subscriptions) throws FirebaseMessagingException {

        List<String> registrationTokens = new ArrayList<>();

        subscriptions.forEach(subscription -> {
            registrationTokens.add(subscription.getToken());
        });

        Notification notification = Notification
                .builder()
                .setTitle(note.getTitle())
                .setBody(note.getMessage())
                .setImage(note.getImageUrl())
                .build();

        MulticastMessage message = MulticastMessage.builder()
                .setNotification(notification)
                .addAllTokens(registrationTokens)
                .build();

        BatchResponse response = firebaseMessaging.sendMulticast(message);
        System.out.println(response.getSuccessCount());

    }

}
