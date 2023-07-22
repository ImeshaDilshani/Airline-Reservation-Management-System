package com.jkshian.arms.repo;

import com.jkshian.arms.dao.UserDao;
import com.jkshian.arms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User>findByEmail(String email);
}
