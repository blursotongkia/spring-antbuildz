package com.smu.antisocial.User;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "api/v1/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping
	public List<User> getUsers(){
        return userService.getUsers();
	}

    @GetMapping(path="{userid}")
    public User getUser(@PathVariable("userid") Integer userid){
        return userService.getUser(userid);
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping(path="{userid}")
    public User updateUser(
        @PathVariable("userid") Integer userid,
        @RequestParam(required=false) String username,
        @RequestParam(required=false) String name, 
        @RequestParam(required=false) String email)
    {
        return userService.updateUser(userid, username, name, email);
    }

    @GetMapping(path="/username/{username}")
    public User verifyPassword(@PathVariable("username") String username, @RequestParam(required = true) String password){
        return userService.verifyPassword(username, password);
    }

    @PutMapping(path="{userid}/verification")
    public void verifyUser(@PathVariable("userid") Integer userid, @RequestParam(required = true) String token){
        userService.verifyUser(userid, token);
    }

    @PutMapping(path="{userid}/requestpartnership")
    public void requestPartnership(@PathVariable("userid") Integer userid){
        userService.requestPartnership(userid);
    }

    @DeleteMapping(path="{userid}")
    public void deleteUser(@PathVariable("userid") Integer userid){
        userService.deleteUser(userid);
    }
}
