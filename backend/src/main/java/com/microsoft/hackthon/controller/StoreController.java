package com.microsoft.hackthon.controller;

import com.microsoft.hackthon.dao.StoreRepository;
import com.microsoft.hackthon.dto.AddressDto;
import com.microsoft.hackthon.dto.StoreDto;
import com.microsoft.hackthon.entity.Product;
import com.microsoft.hackthon.entity.Store;
import com.microsoft.hackthon.service.StoreService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public Store addStore(@Valid @RequestBody StoreDto storeDto, Errors errors){

        if( errors.hasErrors() ){
            throw new RuntimeException("Invalid store data");
        }

        Store store = new Store();
        modelMapper.map(storeDto, store);
        store.setProductList(new ArrayList<>());

        return storeService.addStore(store);
    }
//
//    //    create a method to update a store
    @PutMapping("/stores")
    public Store updateStore(@Valid @RequestBody Store store, Errors errors){
        if (errors.hasErrors()) {
            throw new RuntimeException("Invalid store data");
        }
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

//    @GetMapping("/store/products/address/{address}")
//    public List<Store> getStoreProductsByAddress(@PathVariable String address){
//        return storeRepository.findByStoreAddress(address);
//    }

    @GetMapping("/store/products/filter/address")
    public Set<Store> getStoreByAddressFilter(@RequestBody AddressDto addressDto){

        Set<Store> storeListByCountry = new HashSet<>();
        Set<Store> storeListByState = new HashSet<>();
        Set<Store> storeListByCity = new HashSet<>();

        if( addressDto.getCountry() != null ){
            storeListByCountry = storeRepository.findByStoreAddress_Country(addressDto.getCountry());
        }

        if( storeListByCountry.size() != 0 ){
            if( addressDto.getState() != null ){
                storeListByState = storeRepository.findByStoreAddress_State(addressDto.getState());
                storeListByState.retainAll(storeListByCountry);
            }else{
                storeListByState = storeListByCountry;
            }
        }else{
            System.out.println("Country is null");
            if( addressDto.getState() != null ){
                storeListByState = storeRepository.findByStoreAddress_State(addressDto.getState());
                System.out.println("State is not null");
                System.out.println(storeListByState);
            }
        }

        if( storeListByState.size() != 0 ){
            if( addressDto.getCity() != null ){
                storeListByCity = storeRepository.findByStoreAddress_City(addressDto.getCity());
                storeListByCity.retainAll(storeListByState);
            }else{
                storeListByCity = storeListByState;
            }
        }else{
            if( addressDto.getCity() != null ){
                storeListByCity = storeRepository.findByStoreAddress_City(addressDto.getCity());
            }
        }

        return storeListByCity;
    }


}
