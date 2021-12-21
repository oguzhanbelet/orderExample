package com.orderexample.orderexample.dao;

import com.orderexample.orderexample.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
