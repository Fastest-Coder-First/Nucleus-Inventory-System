package com.microsoft.hackthon.exception;


// package com.microsoft.hackthon.exception;
//
// // create a custom exception class for store not found
// // this class will be used in the controller class

public class StoreNotFound extends RuntimeException{
 //    create a constructor for the class
     public StoreNotFound(String message) {
         super(message);
     }
}
