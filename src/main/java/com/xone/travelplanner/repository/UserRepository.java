package com.xone.travelplanner.repository;

import com.xone.travelplanner.model.User;
import org.hibernate.event.internal.EntityState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    boolean existsByEmail(String email);

    Optional<User> findByEmailAndEntityState(String email, EntityState entityState);
}
