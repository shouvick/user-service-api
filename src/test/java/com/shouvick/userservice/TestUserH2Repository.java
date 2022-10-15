package com.shouvick.userservice;

import com.shouvick.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestUserH2Repository extends JpaRepository<User, String> {
}
