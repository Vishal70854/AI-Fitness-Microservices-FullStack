package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor    // by this we can mention any varialbe as final and its bean will be created automatically by spring at project start
public class RecommendationService {

    // below RecommendationRepository object will automatically be created by spring as per IOC principle
    private final RecommendationRepository recommendationRepository;

    // get list of recommendation for particular user by userId
    public List<Recommendation> getUserRecommendation(String userId) {

        return recommendationRepository.findByUserId(userId);
    }

    // get recommendation for particular user by activityId

    public Recommendation getActivityRecommendation(String activityId) {
        return recommendationRepository.findByActivityId(activityId)
                .orElseThrow(() -> new RuntimeException("No recommendation found for this activity: " + activityId));
    }

}
