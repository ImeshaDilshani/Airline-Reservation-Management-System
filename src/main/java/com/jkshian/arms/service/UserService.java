package com.jkshian.arms.service;

import com.jkshian.arms.User.Role;
import com.jkshian.arms.dto.AuthenticationRequest;
import com.jkshian.arms.entity.User;
import com.jkshian.arms.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    public boolean checkUserRoleIsAdmin(AuthenticationRequest request) {
        User admin = userRepository.findByEmail(request.getEmail()).orElseThrow();
        if(admin.getRole().toString().equals("ROLE_ADMIN")){
            return true;
        }else{
            return false;
        }
    }
}
