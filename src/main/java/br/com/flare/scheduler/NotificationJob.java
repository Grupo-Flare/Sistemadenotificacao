package br.com.flare.scheduler;

import br.com.flare.model.Note;
import br.com.flare.model.Subscription;
import br.com.flare.repository.SubscriptionRepository;
import br.com.flare.service.NotificationSenderService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class NotificationJob extends QuartzJobBean {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private NotificationSenderService notificationSenderService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List<Subscription> all = subscriptionRepository.findAll();
        System.out.println(all);
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        Object note = jobDataMap.get("note");
        try {
            notificationSenderService.sendNotificationToAllUsers((Note) note, all);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
