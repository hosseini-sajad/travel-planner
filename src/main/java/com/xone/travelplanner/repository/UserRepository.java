package com.xone.travelplanner.repository;

import com.xone.travelplanner.model.User;
import org.hibernate.event.internal.EntityState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    
    boolean existsByEmail(String email);

    Optional<User> findByIdAndEntityState(UUID id, EntityState entityState);

    Optional<User> findByEmailAndEntityState(String email, EntityState entityState);
}
