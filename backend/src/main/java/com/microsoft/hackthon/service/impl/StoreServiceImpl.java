package com.microsoft.hackthon.service.impl;

import com.microsoft.hackthon.dao.StoreRepository;
import com.microsoft.hackthon.entity.Store;
import com.microsoft.hackthon.exception.StoreNotFound;
import com.microsoft.hackthon.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// create a service implementation class for store
// this class will be used in the controller class
// we will create the crud operation for the store class in this class
@Service
public class StoreServiceImpl implements StoreService {

    //    inject store repository
    @Autowired
    private StoreRepository storeRepository;

    @Override
    public List<Store> getStores() {
        //        return all stores from database with store repository
        return storeRepository.findAll();
    }

    @Override
    public Store getStore(int id) {
        //        return product with id from database with product repository
        // create a method to get store by id from database with store repository

        return storeRepository.findById(id).orElseThrow(() -> new StoreNotFound("Store not found"));
    }

    @Override
    public Store addStore(Store store) {
//        create  add store method with store repository
        storeRepository.findById(store.getId()).ifPresent(s -> {
            throw new StoreNotFound("Store already exists");
        });

        return storeRepository.save(store);
    }

    @Override
    public Store updateStore(Store store) {
//        create a method to update store to database with store repository
        Store existingStore = storeRepository.findById(store.getId()).orElseThrow(() -> new StoreNotFound("Store not found"));
        existingStore.setStoreName(store.getStoreName());
        existingStore.setStoreAddress(store.getStoreAddress());
        return storeRepository.save(existingStore);
    }

    @Override
    public void deleteStore(int id) {
//        create a method to delete store to database with store repository
        storeRepository.findById(id).orElseThrow(() -> new StoreNotFound("Store not found"));
        storeRepository.deleteById(id);
        return ;
    }
}
