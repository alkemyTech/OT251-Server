package com.alkemy.ong.services.impl;

import com.alkemy.ong.dto.request.activity.ActivityRequest;
import com.alkemy.ong.dto.response.activity.ActivityResponse;
import com.alkemy.ong.mappers.ActivityMapper;
import com.alkemy.ong.models.Activity;
import com.alkemy.ong.repositories.ActivityRepository;
import com.alkemy.ong.services.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService implements IActivityService {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    ActivityMapper activityMapper;

    @Override
    public ActivityResponse create(ActivityRequest activityRequest) {
        Activity activity = activityMapper.activityRequestToActivity(activityRequest);
        return activityMapper.activityToActivityResponse(activityRepository.save(activity));
    }
}
