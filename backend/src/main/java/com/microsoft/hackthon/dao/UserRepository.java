package com.microsoft.hackthon.dao;

import com.microsoft.hackthon.entity.Store;
import com.microsoft.hackthon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
