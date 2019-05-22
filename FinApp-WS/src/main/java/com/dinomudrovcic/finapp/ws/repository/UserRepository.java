package com.dinomudrovcic.finapp.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dinomudrovcic.finapp.ws.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
