package com.resumebuilder.api.controller;

import com.resumebuilder.api.entities.UserEntity;
import com.resumebuilder.api.exception.PasswordNullOrEmptyException;
import com.resumebuilder.api.exception.UserAlreadyExistsExecption;
import com.resumebuilder.api.exception.UserNotFoundException;
import com.resumebuilder.api.exception.UsernameNullOrEmptyException;
import com.resumebuilder.api.helpers.GetCurrentUserIdService;
import com.resumebuilder.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    GetCurrentUserIdService getCurrentUserIdService;

    @PostMapping
    public ResponseEntity saveUser(@RequestBody UserEntity user){
        try{
            return ResponseEntity.ok(userService.saveUser(user));
        }
        catch (UserAlreadyExistsExecption e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (UsernameNullOrEmptyException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (PasswordNullOrEmptyException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Server side error occurred: "+e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("@getCurrentUserIdService.hasId(#id)")
    public ResponseEntity getOneUser(@PathVariable Long id){
        try{
            return ResponseEntity.ok(userService.getOne(id));
        }
        catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Server side error occurred: "+e);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@getCurrentUserIdService.hasId(#id)")
    public ResponseEntity deleteUser(@PathVariable Long id){
        try{
            return ResponseEntity.ok(userService.deleteUser(id));
        }
        catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Server side error occurred: "+e);
        }
    }
}