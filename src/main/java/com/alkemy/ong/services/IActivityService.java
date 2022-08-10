package com.alkemy.ong.services;

import com.alkemy.ong.dto.request.activity.ActivityRequest;
import com.alkemy.ong.dto.response.activity.ActivityResponse;

import java.util.UUID;

public interface IActivityService {

    public ActivityResponse create(ActivityRequest activityRequest);

    public ActivityResponse update(UUID id, ActivityRequest activityRequest);

}
