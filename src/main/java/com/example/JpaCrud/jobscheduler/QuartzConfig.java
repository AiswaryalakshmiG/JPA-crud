package com.example.JpaCrud.jobscheduler;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class QuartzConfig {
	@Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(UserPrintJob.class)
                .withIdentity("userPrintJob")
                .storeDurably()
                .build();
    }

	
	@Bean
	public Trigger jobTrigger() {
		return TriggerBuilder.newTrigger()
				.forJob(jobDetail())
				.withIdentity("userPrintTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInMinutes(1)
                .repeatForever())
                .build();
	}
}
