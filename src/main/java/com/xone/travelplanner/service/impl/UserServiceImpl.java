package com.xone.travelplanner.service.impl;

import com.xone.travelplanner.core.Error;
import com.xone.travelplanner.core.TravelException;
import com.xone.travelplanner.core.UserRole;
import com.xone.travelplanner.model.User;
import com.xone.travelplanner.repository.UserRepository;
import com.xone.travelplanner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.event.internal.EntityState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void register(User user) throws TravelException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new TravelException(Error.EMAIL_IS_ALREADY_IN_USE);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsFirstLogin(true);
        user.setIsActive(true);
        user.setRole(UserRole.User);
        user.setLastLogin(new Date());
        user.setEntityState(EntityState.PERSISTENT);
        userRepository.save(user);
    }

    @Override
    public User findById(UUID id) throws TravelException {
        return userRepository.findByIdAndEntityState(id, EntityState.PERSISTENT)
                .orElseThrow(() -> new TravelException(Error.USER_NOT_FOUND));
    }

    @Override
    @Transactional
    public User login(String email, String password) throws TravelException {
        User user = userRepository.findByEmailAndEntityState(email, EntityState.PERSISTENT)
                .orElseThrow(() -> new TravelException(Error.EMAIL_IS_NOT_VALID));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new TravelException(Error.PASSWORD_IS_NOT_VALID);
        }

        if (user.getIsFirstLogin()) {
            user.setIsFirstLogin(false);
            user = userRepository.save(user);
        }

        return user;
    }
}
