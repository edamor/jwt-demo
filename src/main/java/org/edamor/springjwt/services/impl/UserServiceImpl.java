package org.edamor.springjwt.services.impl;

import org.edamor.springjwt.models.User;
import org.edamor.springjwt.repositories.UserRepository;
import org.edamor.springjwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;


    @Override
    public User addUser(User user) {
        if (userRepo.findByUsername(user.getUsername()) == null) {
            String hashedPw = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt());
            return userRepo.save(new User(user.getUsername(), hashedPw));
        }
        return null;
    }
}
