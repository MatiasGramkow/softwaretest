package com.softwaretest.Repositories;

import com.softwaretest.Models.Product;
import com.softwaretest.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>
{
    User getOneByEmail(String email);

}
