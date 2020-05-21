package com.softwaretest.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Users")
public class User implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    private String password;

    @Column(unique = true)
    private String email;

    private String role;

    private int enabled;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Users_Products",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    Set<User> users = new HashSet<>();


    public User(Long userId, String userName, String password, String email, String role, Set<User> users)
    {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.users = users;
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

    public Set<User> getUsers()
    {
        return users;
    }

    public void setUsers(Set<User> users)
    {
        this.users = users;
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
                ", users=" + users +
                '}';
    }
}
