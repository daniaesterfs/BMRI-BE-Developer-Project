package com.example.bankmandiriproject.repository;

import com.example.bankmandiriproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    @Query(value = "SELECT c FROM mst_user c WHERE c.user_username=:username", nativeQuery = true)
    User findUserByUsername(String username);

}
