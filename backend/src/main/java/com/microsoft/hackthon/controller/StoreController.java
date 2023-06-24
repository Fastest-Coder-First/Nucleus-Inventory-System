package com.microsoft.hackthon.controller;

import com.microsoft.hackthon.dao.StoreRepository;
import com.microsoft.hackthon.dto.StoreDto;
import com.microsoft.hackthon.entity.Product;
import com.microsoft.hackthon.entity.Store;
import com.microsoft.hackthon.service.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/store")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
public class StoreController {

    //    inject store service
    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ModelMapper modelMapper;

//    create a method to get all stores
    @GetMapping("/stores")
    public List<Store> getAllStores(){
        return storeService.getStores();
    }

//    //    create a method to get a store by id
    @GetMapping("/stores/{storeId}")
    public Store getStoreById(@PathVariable int storeId){
        return storeService.getStore(storeId);
    }

    //    create a method to add a store
    @PostMapping("/stores")
    public Store addStore(@RequestBody StoreDto storeDto){
        Store store = new Store();
        modelMapper.map(storeDto, store);
        store.setProductList(new ArrayList<>());

        return storeService.addStore(store);
    }
//
//    //    create a method to update a store
    @PutMapping("/stores")
    public Store updateStore(@RequestBody Store store){
        return storeService.updateStore(store);
    }
//
//    //    create a method to delete a store
    @DeleteMapping("/stores/{storeId}")
    public String deleteStore(@PathVariable int storeId){
        storeService.deleteStore(storeId);
        return "Store deleted with id "+storeId;
    }

    @GetMapping("/store/products/{storeId}")
    public List<Product> getStoreProducts(@PathVariable int storeId){
        return storeService.getStore(storeId).getProductList();
    }

    @GetMapping("/store/products/address/{address}")
    public List<Store> getStoreProductsByAddress(@PathVariable String address){
        return storeRepository.findByStoreAddress(address);
    }


}
