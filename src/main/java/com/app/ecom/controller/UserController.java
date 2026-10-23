package com.app.ecom.controller;

import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.service.UserService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //   Get All the users  ---->
    @GetMapping("/api/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
//        return  ResponseEntity.ok(userService.fetchAllUsers()) ;
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
    }

    //    Creating User --->
    @PostMapping("/api/users")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
        return ResponseEntity.ok("User Added successfully");
    }

    //    Get user bu ID ---->
    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    Update user data by id ---->
    @PutMapping("/api/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        boolean updated = userService.updateUser(id, userRequest);
        if(updated) return ResponseEntity.ok("User Updated Successfully!");
        return ResponseEntity.notFound().build();
    }

}
