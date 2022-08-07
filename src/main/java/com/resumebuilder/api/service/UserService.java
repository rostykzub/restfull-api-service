package com.resumebuilder.api.service;

import com.resumebuilder.api.entities.UserEntity;
import com.resumebuilder.api.exception.*;
import com.resumebuilder.api.helpers.Checks;
import com.resumebuilder.api.model.Resume;
import com.resumebuilder.api.model.User;
import com.resumebuilder.api.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    Checks checks = new Checks();

    @Autowired private UserRepo userRepo;

    public User saveUser(UserEntity user) throws UserAlreadyExistsExecption, UsernameNullOrEmptyException, PasswordNullOrEmptyException {
        if (userRepo.findByEmail(user.getEmail()) != null){
            throw new UserAlreadyExistsExecption("Error: User "+user.getEmail()+" already exists");
        }
        if (checks.isEmptyOrNull(user.getUsername())){
            throw new UsernameNullOrEmptyException("Error: Username is not defined or empty");
        }
        if (checks.isEmptyOrNull(user.getPassword())){
            throw new PasswordNullOrEmptyException("Error: Password is not defined or empty");
        }
        log.info("Saving new user {} to the DB",user.getUsername());
        UserEntity savedUser = userRepo.save(user);
        return User.toModel(savedUser);
    }

    public User getOne(Long id) throws UserNotFoundException {
        if (!userRepo.findById(id).isPresent()){
            throw new UserNotFoundException("Error: User with id "+id+" not found");
        }
        log.info("Getting user {} from the DB",id);
        UserEntity user = userRepo.findById(id).get();
        return User.toModel(user);
    }

    public String deleteUser(Long id) throws UserNotFoundException {
        if (!userRepo.findById(id).isPresent()){
            throw new UserNotFoundException("Error: User with id "+id+" not found");
        }
        log.info("Deleting user {} from the DB",id);
        userRepo.deleteById(id);
        return "User with id "+id+" is successfully deleted";
    }

    public List<User> getAllUsers(){
        log.info("Fetching all users");
        List<UserEntity> users = userRepo.findAll();
        return users.stream().map(User::toModel).collect(Collectors.toList());
    }
}
