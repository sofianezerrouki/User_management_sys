package com.jobtest.me.user_management_account.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Authority {
    @Id
    private Long id;
    @Enumerated
    private Role role;
    @ManyToMany(mappedBy = "authorities")
    private Set<Users> users;
}
