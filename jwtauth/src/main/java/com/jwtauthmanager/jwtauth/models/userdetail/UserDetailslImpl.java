package com.jwtauthmanager.jwtauth.models.userdetail;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.jwtauthmanager.jwtauth.models.entity.User;

public class UserDetailslImpl extends User implements UserDetails {
    
    private static final List<GrantedAuthority> ROLE_USER = Collections
			.unmodifiableList(AuthorityUtils.createAuthorityList("ROLE_USER"));


    public UserDetailslImpl(User user){
        super(user.getUsername(),user.getEmail(),user.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ROLE_USER;
    }



}
