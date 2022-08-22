package com.alkemy.ong.controllers;

import com.alkemy.ong.dto.request.activity.ActivityRequest;
import com.alkemy.ong.dto.response.activity.ActivityResponse;
import com.alkemy.ong.services.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    IActivityService activityService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ActivityResponse> createActivity(@RequestBody @Valid ActivityRequest activityRequest){
        return ResponseEntity.status(HttpStatus.OK).body(activityService.create(activityRequest));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ActivityResponse> updateActivity(@PathVariable UUID id, @RequestBody @Valid ActivityRequest activityRequest){
        return ResponseEntity.status(HttpStatus.OK).body(activityService.update(id, activityRequest));
    }

}
