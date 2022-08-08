package com.alkemy.ong.services;

import com.alkemy.ong.dto.request.activity.ActivityRequest;
import com.alkemy.ong.dto.response.activity.ActivityResponse;

public interface IActivityService {

    public ActivityResponse create(ActivityRequest activityRequest);

}
