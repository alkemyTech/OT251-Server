package com.alkemy.ong.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alkemy.ong.config.amazons3.services.impl.AWSClientServiceImpl;
import com.alkemy.ong.models.vm.Asset;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    @Autowired
    private AWSClientServiceImpl s3Service;

    @PostMapping("/upload")
    Map<String, String> upload(@RequestParam MultipartFile file){
        String key = s3Service.putObject(file);
        Map<String, String> result = new HashMap<>();
        result.put("key",key);
        result.put("url",s3Service.getObjectURL(key));
        return result;
    }

    @GetMapping(value = "/get-object", params = "key")
    ResponseEntity<ByteArrayResource> getObject(@RequestParam String key){
        Asset asset = s3Service.getObject(key);
        ByteArrayResource resource = new ByteArrayResource(asset.getContent());
        return ResponseEntity
            .ok()
            .header("Content-Type", asset.getContentType())
            .contentLength(asset.getContent().length)
            .body(resource);
    }

    @DeleteMapping(value = "/delete-object", params = "key")
    void deleteObject(@RequestParam String key){
        s3Service.deleteObject(key);
    }
}
