package com.simpleform.user.repository;

import com.simpleform.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Optional <UserModel> findByLoginAndPassword(String login , String password);
    Optional <UserModel> findFirstByLogin(String login);

//    Optional <UserModel> findByLoginAndEmail(String login , String Email);
//
//    Optional <UserModel> findById(String login );
//
//    UserModel findAll(String login);
}
