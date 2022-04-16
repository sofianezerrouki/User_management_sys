package com.jobtest.me.user_management_account.repo;

import com.jobtest.me.user_management_account.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username) throws UsernameNotFoundException;

    @Query("SELECT u FROM User u WHERE CONCAT(u.username,u.email) LIKE %:keyword%")
    List<User> search(@Param("keyword") String keyword);
}
