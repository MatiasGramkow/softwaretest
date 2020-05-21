package com.softwaretest.Models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "Products")
public class Product
{
    @Id
    private Long productId;

    private String name;

    @ManyToMany(mappedBy = "users")
    private Set<User> products = new HashSet<>();

    public Product()
    {
    }

    public Product(Long productId, String name, Set<User> products)
    {
        this.productId = productId;
        this.name = name;
        this.products = products;
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

    public Set<User> getProducts()
    {
        return products;
    }

    public void setProducts(Set<User> products)
    {
        this.products = products;
    }

    @Override
    public String toString()
    {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
