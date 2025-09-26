package isil.java_quiz_server.controller;

import isil.java_quiz_server.exception.UserNotFoundException;
import isil.java_quiz_server.modal.User;
import isil.java_quiz_server.repository.UserRepository;
import isil.java_quiz_server.requests.LoginRequest;
import isil.java_quiz_server.response.LoginResponse;
import isil.java_quiz_server.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3001")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<User> newUser(@RequestBody User newUser) {
        User savedUser = userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse loginResponse = loginService.authenticateUser(loginRequest);
            if (loginResponse.isAuthenticated()) {
                return ResponseEntity.ok(loginResponse);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
            }
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
