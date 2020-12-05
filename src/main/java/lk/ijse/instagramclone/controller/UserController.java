package lk.ijse.instagramclone.controller;

import lk.ijse.instagramclone.entity.Login_Logout;
import lk.ijse.instagramclone.entity.User;
import lk.ijse.instagramclone.repository.LoginLogoutRepo;
import lk.ijse.instagramclone.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/instagram")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LoginLogoutRepo loginLogoutRepo;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register_user(@RequestBody User user) {
        if (userRepo.existsUserByEmail(user.getEmail())) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        } else {
            userRepo.save(user);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @PutMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login_user(@RequestBody User user) {
        if (userRepo.existsUserByEmail(user.getEmail())) {
            User byEmail = userRepo.findByEmail(user.getEmail());
            if (byEmail.getPassword().equals(user.getPassword())) {
                loginLogoutRepo.save(new Login_Logout(byEmail.getId(), true));
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @PutMapping(value = "logout/{id}")
    public ResponseEntity logout_user(@PathVariable("id") Integer userId) {
        if (loginLogoutRepo.existsByUserId(userId)) {
            Login_Logout login_logout = loginLogoutRepo.findByUserId(userId);
            login_logout.setStatus(false);
            loginLogoutRepo.save(login_logout);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
