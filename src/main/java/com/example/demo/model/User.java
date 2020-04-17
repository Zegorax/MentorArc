package com.example.demo.model;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "auth_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_user_id")
    private int id;

    @NotNull(message="Username")
    @Column(name = "username")
    private String username;

    @NotNull(message="Email is required")
    @Email(message = "Email is invalid")
    @Column(name = "email")
    private String email;

    @NotNull(message="Password is required")
    @Length(min=5, message="Password should be at least 5 characters")
    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "auth_user_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
    private Set<Role> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public ArrayList<String> getRolesArray(){
        ArrayList<String> returnArray = new ArrayList<String>();
        
        for (Role r : roles){
            returnArray.add(r.getName());
        }

        return returnArray;
    }

    public String getRole(){
        ArrayList<String> rolesarray = getRolesArray();
        return rolesarray.get(0);
    }

    public boolean isPoulain(){
        return getRolesArray().contains("POULAIN");
    }

    public boolean isMentor(){
        return getRolesArray().contains("MENTOR");
    }

    public boolean isAdmin(){
        return getRolesArray().contains("ADMIN");
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
