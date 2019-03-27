package kr.hs.dgsw.demo.Controller;

import kr.hs.dgsw.demo.Domain.User;
import kr.hs.dgsw.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/listalluser")
    public List<User> listAllUsers(){
        return this.userService.listAllUsers();
    }

    @GetMapping("/viewuser/{userId}")
    public User viewUser(@PathVariable Long userId){
        return this.userService.viewUser(userId);
    }

    @PostMapping("/adduser")
    public User addUser(@RequestBody User user){
        return this.userService.addUser(user);
    }

    @PutMapping("/updateuser")
    public User updateUser(@RequestBody User user){
        return this.userService.updateUser(user);
    }

    @DeleteMapping("/deleteuser/{userId}")
    public boolean deleteUser(@PathVariable Long userId){
        return this.userService.deleteUser(userId);
    }



}
