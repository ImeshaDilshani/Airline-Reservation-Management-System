package com.jkshian.arms.registration;

import com.jkshian.arms.applicationUser.AppUser;
import com.jkshian.arms.applicationUser.AppUserRole;
import com.jkshian.arms.applicationUser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private EmailValidartor emailValidartor;
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidartor.
                test(request.getEmail());
        if (!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        return appUserService.signUpUser(new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER
                )
        );
    }
}
