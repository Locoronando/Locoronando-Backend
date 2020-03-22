package org.wirvsvirus.locoronando.customer;

import org.wirvsvirus.locoronando.customer.model.Customer;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<Customer> customers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Customer> getUsers() {
        return customers;
    }

    public void setUsers(Set<Customer> users) {
        this.customers = users;
    }
}