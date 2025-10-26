package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor    // create bean automatically by making variable as final for dependency injection
public class ActivityMessageListener {

    private final ActivityAIService aiService;  // this bean will be automatically created as it is declared final and also @RequiredArgsConstructor is used at class level

    @RabbitListener(queues = "activity.queue") // this annotation will listen to the rabbitMQ messages from specified queue name
    public void processActivity(Activity activity){
        log.info("Received activity for processing: {}", activity.getId());
        log.info("Generated Recommendation: {}", aiService.generateRecommendation(activity));

    }

}
