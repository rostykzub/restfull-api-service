package com.resumebuilder.api.auth;

import com.resumebuilder.api.entities.UserEntity;
import com.resumebuilder.api.repo.UserRepo;
import com.resumebuilder.api.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private UserRepo userRepo;
    private JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager, UserRepo userRepo, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(value = "/login", consumes = {"application/json"})
    public ResponseEntity<?> authenticate(@RequestBody AuthRequestDTO request){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            UserEntity user = userRepo.findByEmail(request.getEmail());
            System.out.println(user);
            System.out.println(user.getId());
            String token = jwtTokenProvider.createToken(request.getEmail(),user.getId().toString());
            Map<Object,Object> response = new HashMap<>();
            response.put("userId",user.getId().toString());
            response.put("email",request.getEmail());
            response.put("token",token);
            return ResponseEntity.ok(response);
        }
        catch (AuthenticationException e){
            return new ResponseEntity<>("Invalid email or password", HttpStatus.FORBIDDEN);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request,response,null);
    }

}
