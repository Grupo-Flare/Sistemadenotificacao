package br.com.flare.scheduler;

import br.com.flare.model.Note;
import br.com.flare.service.NotificationSenderService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class NotificationJob extends QuartzJobBean {

    @Autowired
    private NotificationSenderService notificationSenderService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        Object note = jobDataMap.get("note");
        try {
            notificationSenderService.sendNotificationToAllUsers((Note) note);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
