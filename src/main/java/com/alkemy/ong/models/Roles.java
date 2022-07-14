package com.alkemy.ong.models;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;

public class Roles {

    @NonNull
    private String name;

    @Nullable
    private String descriptions;

    private Timestamp timestamp;

}
