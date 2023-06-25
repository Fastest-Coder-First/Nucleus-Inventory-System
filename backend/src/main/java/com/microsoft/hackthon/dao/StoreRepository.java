package com.microsoft.hackthon.dao;

import com.microsoft.hackthon.entity.Address;
import com.microsoft.hackthon.entity.Product;
import com.microsoft.hackthon.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, Integer> {
//    List<Store> findByStoreAddress(Address storeAddress);

    Set<Store> findByStoreAddress_Country(String country);

    Set<Store> findByStoreAddress_State(String state);

    Set<Store> findByStoreAddress_City(String city);

    Set<Store> findByStoreAddress_CountryAndStoreAddress_State(String country, String state);

    Set<Store> findByStoreAddress_CountryAndStoreAddress_City(String country, String city);

    Set<Store> findByStoreAddress_StateAndStoreAddress_City(String state, String city);

    Set<Store> findByStoreAddress_CountryAndStoreAddress_StateAndStoreAddress_City(String country, String state, String city);



}
