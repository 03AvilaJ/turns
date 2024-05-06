package edu.uptc.swii.usermicroservice.controller;

import edu.uptc.swii.usermicroservice.entity.User;
import edu.uptc.swii.usermicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/allUsers")
    @PreAuthorize("hasRole('Administrators_clientRole')")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/userBy/{userId}")
    @PreAuthorize("hasRole('Administrators_clientRole')")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Integer id){
        User user = userService.getUserbyId(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/saveUser")
    @PreAuthorize("hasRole('Administrators_clientRole') or hasRole('users_clientRole')" )
    public  ResponseEntity<User> createUser(@RequestBody User user){
        User userSave = userService.saveUser(user);
        return ResponseEntity.ok(userSave);
    }

    @DeleteMapping("/deleteUser/{userId}")
    @PreAuthorize("hasRole('Administrators_clientRole')")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
        try {
            userService.deleteUserById(userId);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar usuario: " + e.getMessage());
        }
    }
}
