package com.jobtest.me.user_management_account.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    //todo add validation

    @Id
    private String username;
    private String password;
    private String email;

    @ManyToMany
    Set<Authorities> authorities = new HashSet<>();


}
