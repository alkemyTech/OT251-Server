package com.alkemy.ong.mappers;

import com.alkemy.ong.dto.request.activity.ActivityRequest;
import com.alkemy.ong.dto.response.activity.ActivityResponse;
import com.alkemy.ong.models.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {

    public Activity activityRequestToActivity(ActivityRequest activityRequest){
        if(activityRequest==null)
            return null;
        Activity activity = new Activity();
        activity.setName(activityRequest.getName());
        activity.setContent(activityRequest.getContent());
        activity.setImage(activityRequest.getImage());
        return activity;
    }

    public ActivityResponse activityToActivityResponse(Activity activity){
        if(activity==null)
            return null;
        ActivityResponse activityResponse = new ActivityResponse();
        activityResponse.setId(activity.getId());
        activityResponse.setName(activity.getName());
        activityResponse.setContent(activity.getContent());
        activityResponse.setImage(activity.getImage());
        return activityResponse;
    }
}
