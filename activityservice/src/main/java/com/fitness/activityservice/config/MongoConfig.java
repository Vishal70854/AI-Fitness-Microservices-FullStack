package com.fitness.activityservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing    // this we have to do inorder to fetch createdAt and updatedAt field
public class MongoConfig {
}
