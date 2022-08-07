package com.resumebuilder.api.helpers;

import com.resumebuilder.api.entities.UserEntity;
import com.resumebuilder.api.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetCurrentUserIdService {

    @Autowired
    private UserRepo userRepo;

    public boolean hasId(Long userId){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepo.findByUsername(username);
        log.info("username is:"+username);
        log.info("am I able to access this?:" + user.getId().equals(userId));
        return user.getId().equals(userId);
    }
}
