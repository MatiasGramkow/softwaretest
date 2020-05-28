package com.softwaretest.Repositories;

import com.softwaretest.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends JpaRepository<User, Long>
{
    User getOneByEmail(String email);

    @Transactional
    User getOneByUserId(Long id);
}
