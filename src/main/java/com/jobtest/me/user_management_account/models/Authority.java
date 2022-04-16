package com.jobtest.me.user_management_account.models;


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
    @Enumerated
    private Role role;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Authority(Role role) {
        this.role = role;
    }
}
