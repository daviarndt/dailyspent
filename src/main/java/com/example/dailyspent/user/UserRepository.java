package com.example.dailyspent.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserDetails> findByEmail(String login);

    @Query(
            value = "SELECT * FROM USER WHERE EMAIL=:email",
            nativeQuery = true
    )
    Optional<UserModel> findByEmailNative(@Param("email") String email);
}
