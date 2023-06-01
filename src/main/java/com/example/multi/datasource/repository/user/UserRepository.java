package com.example.multi.datasource.repository.user;

import com.example.multi.datasource.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
