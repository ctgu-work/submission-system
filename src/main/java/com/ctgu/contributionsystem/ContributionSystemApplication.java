package com.ctgu.contributionsystem;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@EnableScheduling
@SpringBootApplication
public class ContributionSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContributionSystemApplication.class, args);
    }

    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler sc = new ThreadPoolTaskScheduler();
        sc.setPoolSize(10);
        sc.setThreadNamePrefix("springboot-task");
        return sc;
    }
}
