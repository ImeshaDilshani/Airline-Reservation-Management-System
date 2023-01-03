package com.jkshian.arms.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class EmailValidartor implements Predicate< String> {
    @Override
    public boolean test(String s) {
//      TODO: Regex to validate email
        return true;
    }
}
