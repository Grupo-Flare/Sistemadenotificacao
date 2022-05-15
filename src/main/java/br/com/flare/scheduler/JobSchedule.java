package br.com.flare.scheduler;

import br.com.flare.model.Note;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JobSchedule {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    public void scheduleNewJob(Note note, LocalDateTime localDateTime) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobDetail jobDetail = JobBuilder
                .newJob(NotificationJob.class)
                .withIdentity(note.getTitle(), note.getTitle() + "Scheduled")
                .build();

        jobDetail.getJobDataMap().put("note", note);

        Trigger trigger = TriggerBuilder.newTrigger()
                .startAt(convertToDate(localDateTime))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

    }

    private Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
