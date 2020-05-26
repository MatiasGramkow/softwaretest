package com.softwaretest.Models;

import com.softwaretest.Exceptions.ErrorPrerequisites;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.*;

import static com.softwaretest.Exceptions.Constants.*;

@Entity
@Table(name = "Users")
public class User implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Size(min = 5, message = USERNAME_TOO_SHORT)
    @Size(max = 20, message = USERNAME_TOO_LONG)
    private String userName;

    @Size(min = 8, message = PASSWORD_TOO_SHORT)
    private String password;

    @Transient
    @Size(min = 8, message = PASSWORD_TOO_SHORT)
    private String retypePassword;

    @Column(unique = true)
    @Email
    @Size(max = 255, message = EMAIL_TOO_LONG)
    private String email;

    private String role;

    private int enabled;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Products_Users",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    Set<Product> products = new HashSet<>();


    public User(Long userId, String userName, String password, String email, String role, Set<Product> products)
    {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.products = products;
        this.enabled = 1;
    }

    public User(Long userId, String userName, String password, String email, String role, int enabled)
    {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.enabled = enabled;
    }

    public User() {
        this.enabled = 1;
    }

    public int getEnabled()
    {
        return enabled;
    }

    public void setEnabled(int enabled)
    {
        this.enabled = enabled;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public Set<Product> getProducts()
    {
        return products;
    }

    public String getRetypePassword()
    {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword)
    {
        this.retypePassword = retypePassword;
    }

    public void setProducts(Set<Product> products)
    {
        this.products = products;
    }
    @Override
    public String toString()
    {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", products=" + products +
                '}';
    }
}
