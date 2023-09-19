package com.jkshian.arms.entity;

import com.jkshian.arms.User.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name =  "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

    @Getter
    private String firstName;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Getter
    private String lastName;

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Getter
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    private String password;
    public void setPassword(String password) {
        this.password = password;
    }


     @Enumerated(EnumType.STRING)
     private Role role;
     public User(int id,String firstName,String lastName,String email,String password){
         this.id=id;
         this.firstName=firstName;
         this.lastName=lastName;
         this.email=email;
         this.password=password;
     }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
