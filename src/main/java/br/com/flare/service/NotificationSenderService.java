package br.com.flare.service;

import br.com.flare.model.Note;
import br.com.flare.model.Subscription;
import br.com.flare.repository.ChannelRepository;
import br.com.flare.repository.SubscriptionRepository;
import br.com.flare.scheduler.JobSchedule;
import com.google.firebase.messaging.*;
import org.apache.commons.lang3.NotImplementedException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationSenderService {

    private final FirebaseMessaging firebaseMessaging;
    private final SubscriptionRepository subscriptionRepository;
    private final ChannelRepository channelRepository;
    private final JobSchedule jobSchedule;

    @Autowired
    public NotificationSenderService(FirebaseMessaging firebaseMessaging, SubscriptionRepository subscriptionRepository, ChannelRepository channelRepository, JobSchedule jobSchedule) {
        this.firebaseMessaging = firebaseMessaging;
        this.subscriptionRepository = subscriptionRepository;
        this.channelRepository = channelRepository;
        this.jobSchedule = jobSchedule;
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

    public void sendNotificationToAllUsers(Note note) throws FirebaseMessagingException {

        List<Subscription> subscriptions = subscriptionRepository.findAll();

        List<String> registrationTokens = new ArrayList<>();

        subscriptions.forEach(subscription -> registrationTokens.add(subscription.getToken()));

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

    }

    public void sendNotificationToCategory(Note note) {

        throw new NotImplementedException("Ainda não foi implementado");

    }
    public void scheduleNotificationSending(Note note, LocalDateTime localDateTime) throws SchedulerException {
        jobSchedule.scheduleNewJob(note, localDateTime);
    }

    public void scheduleNotificationSending(Note note) throws SchedulerException {
        jobSchedule.scheduleNewJob(note, note.getDate());
    }

}
