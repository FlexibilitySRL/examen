package com.example.core.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "client")
@Where(clause = "active <> 0")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 60)
    private String fullname;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private Date createdAt;
    @Column
    private Integer standing;
    @Column
    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getStanding() {
        return standing;
    }

    public void setStanding(Integer standing) {
        this.standing = standing;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Transient
    public static Client getClientDefault() {
        Client client = new Client();
        client.setId(1L);
        client.setActive(Boolean.TRUE);
        client.setUsername("username");
        client.setFullname("Homero");
        client.setCreatedAt(new Date());
        client.setStanding(10);
        client.setPassword("pass1234");

        return client;
    }
}
