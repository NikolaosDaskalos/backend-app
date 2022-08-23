package com.example.backenddemo.repositories;

import com.example.backenddemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{

    public User findByEmail(String email);


}
