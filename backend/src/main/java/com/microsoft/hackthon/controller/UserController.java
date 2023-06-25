package com.microsoft.hackthon.controller;

import com.microsoft.hackthon.config.JwtService;
import com.microsoft.hackthon.dao.UserRepository;
import com.microsoft.hackthon.dto.CustomErrorResponse;
import com.microsoft.hackthon.dto.LoginUserDto;
import com.microsoft.hackthon.dto.UserDto;
import com.microsoft.hackthon.entity.User;
import com.microsoft.hackthon.exception.UserNotFound;
import com.microsoft.hackthon.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/add-user")
    public CustomErrorResponse addUser(@RequestBody UserDto userDto){
        User user = new User();
        modelMapper.map(userDto, user);
//        modelMapper.map( userDto.getAddress(), user.getAddress() );

//        user.setPassword( passwordEncoder.encode( user.getPassword() ) );
        user.setAuthority("ROLE_USER");


        userService.addUser(user);
        return new CustomErrorResponse("user Added", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomErrorResponse> login(@RequestBody LoginUserDto loginUserDto, HttpServletResponse response){

        User user = userRepository.findByEmail(loginUserDto.getEmail()).orElseThrow(() -> new UserNotFound("User not found"));
        if( !user.getPassword().equals(loginUserDto.getPassword()) ){
            throw new RuntimeException("Invalid password");
        }

        if( user.getAuthority().equals("ROLE_USER") ){
            HttpCookie cookie = ResponseCookie.from("user-id", "User")
                    .path("/")
                    .build();
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(new CustomErrorResponse("user Logged In", HttpStatus.OK));
        }else{
            HttpCookie cookie = ResponseCookie.from("admin-id","Admin" )
                    .path("/")
                    .build();
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(new CustomErrorResponse("user Logged In", HttpStatus.OK));
        }
    }

    public CustomErrorResponse updateUser(@RequestBody UserDto userDto){
        User user = new User();
        modelMapper.map(userDto, user);

        userService.updateUser(user);
        return new CustomErrorResponse("user Updated", HttpStatus.CREATED);
    }

//    create readme.md file in the root folder of the project
//  add the following content to the readme.md file
//  # Hackathon Project - Team 1
//  ## Project Description
//  ### Problem
//  - The COVID-19 pandemic has caused a shortage of many products in many stores.
//  ### Solution
//  - Create a platform that allows users to share the availability of products in stores.
//  ### Features
//  - Users can add stores.
//  - Users can add products to stores.
//  - Users can view all products in a store.
//  - Users can view all stores.
//  - Users can view all products.
//  - Users can view all products in all stores.
//  ### Technologies
//  - Spring Boot
//  - MySQL
//  ### User Stories
//  - As a user, I want to add a store.
//  - As a user, I want to add a product to a store.
//  - As a user, I want to view all products in a store.
//  - As a user, I want to view all stores.
//  - As a user, I want to view all products.
//  - As a user, I want to view all products in all stores.
//  ### User Journey
//  - User opens the app.
//  - User clicks on add store.
//  - User enters store info.
//  - User clicks on add product.
//  - User enters product info.
//  - User clicks on view products.
//  - User views all products in the store.
//  - User clicks on view stores.
//  - User views all stores.
//  - User clicks on view all products.
//  - User views all products.
//  - User clicks on view all products in all stores.
//  - User views all products in all stores.
//  ### Entity Relationship Diagram
//  ![ERD](./ERD.jpg)
//  ### Wireframe
//  ![Wireframe](./Wireframe.jpg)
//  ### Flowchart
//  ![Flowchart](./Flowchart.jpg)
//  ### Project Management
//  - [Trello Board](https://trello.com/b/6u9QX8Rl/hackathon-project-team-1)
//

//    create readme.md file in the root folder of the project and add the following content to the readme.md file and some images

//    create a flowchart.jpg file in the root folder of the project and add the following content to the flowchart.jpg file

//    add the usauge of github copilot to the readme.md file


//    create the flow of this project in readme.md file

}
