package com.example.demo.services;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User create(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return user;
        } else if (userRepository.findByEmail(user.getEmail()) != null) {
            return user;
        }

        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            return user;
        }

        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User LoginUser(User user) {
        User us1 = userRepository.findByUsername(user.getUsername());

        if (us1 != null && passwordEncoder().matches(user.getPassword(), us1.getPassword())) {
            return us1;
        }

        return user;
    }

    public List<User> getStandardUsers() {
        return userRepository.findAllStandardUsers();
    }
}
