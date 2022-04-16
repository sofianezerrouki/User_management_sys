package com.jobtest.me.user_management_account.repo;

import com.jobtest.me.user_management_account.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Authority, Long> {
    Authority findByRoleName(String role);
}
