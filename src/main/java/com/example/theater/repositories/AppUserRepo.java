package com.example.theater.repositories;

import com.example.theater.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    public AppUser findByUsername(String username);
}
