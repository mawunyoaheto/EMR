package com.raymond.emrs.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    private String userName;
    private String password;
    private UserStatus active;
    private List<GrantedAuthority> authorities;

    public MyUserDetails() {
    }

    public MyUserDetails(User userDetails) {
        this.userName=userDetails.getUserName();
        this.password =userDetails.getPassword();
        this.active=userDetails.getUserStatus();
//        this.authorities = userDetails.
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active==UserStatus.ACTIVE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active==UserStatus.ACTIVE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active==UserStatus.ACTIVE;
    }

    @Override
    public boolean isEnabled() {
        return active==UserStatus.ACTIVE;
    }
}
