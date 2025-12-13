package com.xone.travelplanner.service;

import com.xone.travelplanner.core.TravelException;
import com.xone.travelplanner.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void register(User user) throws TravelException;

    User findByEmail(String email) throws TravelException;
    
    User login(String email, String password) throws TravelException;
}
