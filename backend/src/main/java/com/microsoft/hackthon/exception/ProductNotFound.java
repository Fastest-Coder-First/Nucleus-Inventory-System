package com.microsoft.hackthon.exception;

// create a custom exception class for product not found
// this class will be used in the controller class

public class ProductNotFound extends RuntimeException{

//    create a constructor for the class
    public ProductNotFound(String message) {
        super(message);
    }

}
// Compare this snippet from src\main\java\com\microsoft\hackthon\exception\productNotFound.java:
// package com.microsoft.hackthon.exception;
//
// // create a custom exception class for product not found
// // this class will be used in the controller class
//
// public class productNotFound extends RuntimeException{
//
// //    create a constructor for the class
//     public productNotFound(String message) {
//         super(message);
//     }
//
// }

// Compare this snippet from src\main\java\com\microsoft\hackthon\exception\storeNotFound.java:
// package com.microsoft.hackthon.exception;
//
// // create a custom exception class for store not found
// // this class will be used in the controller class
//
// public class storeNotFound extends RuntimeException{
//
// //    create a constructor for the class
//     public storeNotFound(String message) {
//         super(message);
//     }
//
// }
// Compare this snippet from src\main\java\com\microsoft\hackthon\controller\ProductController.java:
// package com.microsoft.hackthon.controller;
//
// import com.microsoft.hackthon.entity.Product;
// import com.microsoft.hackthon.exception.productNotFound;
// import com.microsoft.hackthon.service.ProductService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
//
// import java.util.List;
// import java.util.UUID;
//
// @RestController
// @RequestMapping("/api")
// public class ProductController {
//
//     //    inject product service
//     @Autowired
//     private ProductService productService;
//
//     //    get all products
//     @GetMapping("/products")
//     public List<Product> getAllProducts() {
//         return productService.getProducts();
//     }
//
//     //    get product by id
//     @GetMapping("