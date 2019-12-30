package fr.nextoo.micro.user.controller;

import fr.nextoo.micro.common.dto.user.UserDto;
import fr.nextoo.micro.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Collection<UserDto>> getUsers(){
        return ResponseEntity.ok(userService.getAll());
    }
}
