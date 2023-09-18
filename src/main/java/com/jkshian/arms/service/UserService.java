package com.jkshian.arms.service;

import com.jkshian.arms.entity.User;
import com.jkshian.arms.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    public void save(User u){userRepository.save(u);}
    
    public List<User> getAllUsers() {
        List<User> users =userRepository.findAll();
        ResponseEntity.ok();
        return users;
    }
}
