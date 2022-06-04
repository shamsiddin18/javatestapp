package com.simpleform.user.service;

import com.simpleform.security.UserPasswordEncoder;
import com.simpleform.user.model.UserModel;
import com.simpleform.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private UserRepository usersRepository;

    private PasswordEncoder passwordEncoder;

    public UserService (UserRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserModel userModel){
        userModel.setPassword(this.passwordEncoder.encode(userModel.getPassword()));
        usersRepository.save(userModel);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = this.usersRepository.findFirstByLogin(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User is not found");
        }

        return user;
    }
}
