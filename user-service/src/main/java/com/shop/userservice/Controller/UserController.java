package com.shop.userservice.Controller;

import com.shop.userservice.Model.User;
import com.shop.userservice.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("save")
    public User saveUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @PutMapping("update")
    public User updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    @DeleteMapping("delete")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUserById(id);
    }


}
