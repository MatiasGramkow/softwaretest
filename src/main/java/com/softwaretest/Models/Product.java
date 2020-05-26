package com.softwaretest.Models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Products")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;

    private byte[] image;

    private String description;

    @ManyToMany(mappedBy = "products")
    private Set<User> users = new HashSet<>();

    public Product()
    {
    }

    public Product(Long productId, String name)
    {
        this.productId = productId;
        this.name = name;
    }

    public Product(Long productId, String name, byte[] image, Set<User> users, String description)
    {
        this.productId = productId;
        this.name = name;
        this.image = image;
        this.users = users;
        this.description = description;
    }

    public Product(String name)
    {
        this.name = name;
    }

    public byte[] getImage()
    {
        return image;
    }

    public void setImage(byte[] image)
    {
        this.image = image;
    }

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set<User> getUsers()
    {
        return users;
    }

    public void setUsers(Set<User> products)
    {
        this.users = products;
    }

    @Override
    public String toString()
    {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", products=" + users +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
