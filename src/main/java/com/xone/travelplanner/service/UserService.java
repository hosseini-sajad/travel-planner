package com.xone.travelplanner.service;

import com.xone.travelplanner.core.TravelException;
import com.xone.travelplanner.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {
    void register(User user) throws TravelException;

    User findById(UUID id) throws TravelException;
    
    User login(String email, String password) throws TravelException;
}
