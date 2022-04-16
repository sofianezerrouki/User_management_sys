package com.jobtest.me.user_management_account.repo;

import com.jobtest.me.user_management_account.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
}
