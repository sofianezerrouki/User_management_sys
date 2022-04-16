package com.jobtest.me.user_management_account.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    //todo add validation

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Authority> roles;

    @Override
    public Set<? extends GrantedAuthority> getAuthorities() {
        return getGrantedAuthorities(roles);
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

    private Set<? extends GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
        Set<GrantedAuthority> myAuthorities = new HashSet<>();
        for (Authority authority : authorities) {
            myAuthorities.add(new SimpleGrantedAuthority(authority.getRoleName()));
        }
        return myAuthorities;
    }
    public Set<Authority> addAuthority(Authority authority) {
        roles = new HashSet<>();
        roles.add(authority);
        return roles;
    }
}
