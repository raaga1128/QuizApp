package isil.java_quiz_server.service;

import isil.java_quiz_server.exception.UserNotFoundException;
import isil.java_quiz_server.modal.User;
import isil.java_quiz_server.repository.UserRepository;
import isil.java_quiz_server.requests.LoginRequest;
import isil.java_quiz_server.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        Optional<User> optionalUser = userRepository.findByUsername(loginRequest.getUsername());

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        User user = optionalUser.get();

        if (user.getPassword().equals(loginRequest.getPassword())) {
            return new LoginResponse(user, true);
        } else {
            return new LoginResponse(null, false);
        }
    }
}
