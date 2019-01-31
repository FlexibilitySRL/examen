package com.example.core.repository;

import com.example.core.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client c where c.username = :user and c.password = :pass")
    public Client findClientByUsernameAndPassword(@Param("user") String user, @Param("pass") String pass);

    @Query("select c from Client c where c.username = :user")
    public Client findClientByUsername(@Param("user") String user);

    @Modifying
    @Query("update Client set active = :active where username = :username")
    public void update(@Param("username") Long id, @Param("active") Boolean active);
}
