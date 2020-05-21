package com.softwaretest.Repositories;

import com.softwaretest.Models.Product;
import com.softwaretest.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
}
