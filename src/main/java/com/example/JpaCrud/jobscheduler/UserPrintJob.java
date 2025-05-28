package com.example.JpaCrud.jobscheduler;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.JpaCrud.model.User;
import com.example.JpaCrud.repository.UserRepository;

@Component	
public class UserPrintJob implements Job {
    private UserRepository userRepository;
    
    @Autowired
    public void setUserRepository (UserRepository userRepository) {
    	this.userRepository = userRepository;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        List<User> users = userRepository.findAll();
        System.out.println("users");
        for (User user : users) {
            System.out.println(user.getName());
        }
        System.out.println("working..");
}
}
