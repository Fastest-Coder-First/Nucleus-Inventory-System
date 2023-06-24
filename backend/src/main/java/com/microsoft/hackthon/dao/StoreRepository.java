package com.microsoft.hackthon.dao;

import com.microsoft.hackthon.entity.Product;
import com.microsoft.hackthon.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, Integer> {

}
