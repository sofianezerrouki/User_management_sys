package com.jobtest.me.user_management_account.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String roleName;
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Authority(String role) {
        this.roleName = role;
    }
}
