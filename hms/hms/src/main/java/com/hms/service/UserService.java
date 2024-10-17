package com.hms.service;

import com.hms.entity.AppUser;
import com.hms.payload.LoginDto;
import com.hms.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private AppUserRepository appUserRepository;

    public UserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public boolean verifyLogin(LoginDto dto) {
        Optional<AppUser> opuser = appUserRepository.findByUsername(dto.getUsername());
        if (opuser.isPresent()) {
            AppUser appUser = opuser.get();
            return BCrypt.checkpw(dto.getPassword(), appUser.getPassword());
        } else {
            return false;
        }

    }
}
