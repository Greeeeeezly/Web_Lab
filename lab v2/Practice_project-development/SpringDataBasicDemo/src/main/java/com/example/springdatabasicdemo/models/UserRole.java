package com.example.springdatabasicdemo.models;

import com.example.springdatabasicdemo.enums.Role;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity{

    @Column(name = "name" ,nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userRole")
    private List<User> user;

    protected UserRole() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public UserRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "role=" + role +
                '}';
    }

}
