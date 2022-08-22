package com.alkemy.ong.utils;

public interface ApiConstants {

    String ROLE_ADMIN = "hasRole('ROLE_ADMIN')";
    String ROLE_USER = "hasRole('ROLE_USER')";

    String BOTH = "hasAnyRole('ROLE_ADMIN','ROLE_USER')";

    //Paths
    String PATH_TESTIMONIALS = "http://localhost:8080/testimonials/get-all?page=%d";
    String PATH_MEMBERS = "http://localhost:8080/members?page=%d";
    String PATH_NEWS = "http://localhost:8080/news?page=%d";

}