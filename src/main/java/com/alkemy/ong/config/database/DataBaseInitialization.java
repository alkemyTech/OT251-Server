package com.alkemy.ong.config.database;

import com.alkemy.ong.models.Role;
import com.alkemy.ong.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInitialization implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepo;

    @Override
    public void run(String...args) throws Exception {
        if(roleRepo.findByName("USER") == null){
            Role user = new Role();
            user.setName("USER");
            user.setDescription("User default.");
            roleRepo.save(user);
        }
        if(roleRepo.findByName("ADMIN") == null){
            Role admin = new Role();
            admin.setName("ADMIN");
            admin.setDescription("User with administrator permissions.");
            roleRepo.save(admin);
        }
    }
}
