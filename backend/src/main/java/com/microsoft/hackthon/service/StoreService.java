package com.microsoft.hackthon.service;

import com.microsoft.hackthon.entity.Store;

import java.util.List;

public interface StoreService {
    //    create crud operation on store class
    public List<Store> getStores();
    public Store getStore(int id);
    public Store addStore(Store store);
    public Store updateStore(Store store);
    public void deleteStore(int id);
}
