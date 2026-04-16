//package com.example.disasterrelief.service;
//
//import com.example.disasterrelief.dao.CitizenRepository;
//import com.example.disasterrelief.entity.User;
//import com.example.disasterrelief.dao.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.List;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//
//
//    public User createUser(User user) {
//
//        return userRepository.save(user)
//                ;
//    }
//
//    public User getUserById(int userId) {
//
//        return userRepository.findById(userId).orElse(null);
//    }
//
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    public User updateUser(User user) {
//        return userRepository.save(user);
//    }
//
//    public void deleteUser(int userId) {
//        userRepository.deleteById(userId);
//    }
//}


package com.example.disasterrelief.service;


import com.example.disasterrelief.dao.UserRepository;
import com.example.disasterrelief.dto.request.LoginRequestDTO;
import com.example.disasterrelief.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    public String UserLoginValidation(LoginRequestDTO user) {
        System.out.println(user.getEmail() +" password:"+user.getPassword());
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if(authentication.isAuthenticated()) {
            User loggedUser = userRepository.findByEmail(user.getEmail()).orElse(null);
            if(loggedUser != null) {
                return jwtService.generateToken(user.getEmail(), loggedUser.getRole().name());
            }
        }
        return "fail";
    }
    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }
}