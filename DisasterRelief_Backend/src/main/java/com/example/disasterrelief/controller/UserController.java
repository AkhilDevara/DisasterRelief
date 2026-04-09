package com.example.disasterrelief.controller;
import com.example.disasterrelief.dto.request.UserRequestDTO;
import com.example.disasterrelief.entity.User;
import com.example.disasterrelief.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ CREATE USER WITH VALIDATION
    @PostMapping("/createUser")
    public User createUser(@Valid @RequestBody UserRequestDTO requestDTO) {

        User user = new User();
        user.setName(requestDTO.getName());
        user.setRole(requestDTO.getRole());
        user.setEmail(requestDTO.getEmail());
        user.setPhone(requestDTO.getPhone());
        user.setPassword(requestDTO.getPasswordHash());
        user.setStatus(requestDTO.getStatus());

        return userService.createUser(user);
    }
    @PostMapping("/login")
    public String UserLoginValidation(@RequestBody  User user){
        System.out.println(user.getEmail()+" "+user.getPassword());
        return userService.UserLoginValidation(user);
    }
    @GetMapping("/getByUserId/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // ✅ UPDATE USER WITH VALIDATION
    @PutMapping("/update/{id}")
    public User updateUser(
            @PathVariable int id,
            @Valid @RequestBody UserRequestDTO requestDTO) {

        User user = new User();
        user.setUserId(id);
        user.setName(requestDTO.getName());
        user.setRole(requestDTO.getRole());
        user.setEmail(requestDTO.getEmail());
        user.setPhone(requestDTO.getPhone());
        user.setPassword(requestDTO.getPasswordHash());
        user.setStatus(requestDTO.getStatus());

        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        System.out.println("user deleted");
    }
}


//package com.example.disasterrelief.controller;
//
//import com.example.disasterrelief.dto.request.UserRequestDTO;
//import com.example.disasterrelief.entity.User;
//import com.example.disasterrelief.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/createUser")
//    public User createUser(@RequestBody UserRequestDTO user) {
//        System.out.println(user.getName()+" "+user.getPassword());
//        return userService.createUser(user);
//    }
//
//    @GetMapping("/getByUserId/{id}")
//    public User getUserById(@PathVariable int id) {
//        return userService.getUserById(id);
//    }
//
//    @GetMapping("/getAllUsers")
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    @PutMapping("/update/{id}")
//    public User updateUser(@PathVariable int id, @RequestBody User user) {
//        System.out.println(user.getPassword());
//        user.setUserId(id);
//        return userService.updateUser(user);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public void deleteUser(@PathVariable int id) {
//        userService.deleteUser(id);
//        System.out.println("user delted");
//    }
//}