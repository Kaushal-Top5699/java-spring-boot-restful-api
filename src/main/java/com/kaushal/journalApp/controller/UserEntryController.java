package com.kaushal.journalApp.controller;

import com.kaushal.journalApp.entity.UserEntry;
import com.kaushal.journalApp.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserEntryController {

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping("/get-all-users")
    public ResponseEntity<List<UserEntry>> getAllUsers() {
        if (!userEntryService.getAllUsers().isEmpty()) {
            return new ResponseEntity<>(userEntryService.getAllUsers(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-new-user")
    public ResponseEntity<UserEntry> createNewUser(@RequestBody UserEntry userEntry) {
        try {
            return new ResponseEntity<>(userEntryService.saveUser(userEntry), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<UserEntry>> findUserById(@PathVariable ObjectId id) {
        Optional<UserEntry> user = userEntryService.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<UserEntry>> deleteUserById(@PathVariable ObjectId id) {
        Optional<UserEntry> user = userEntryService.deleteById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update-password/{username}")
    public ResponseEntity<UserEntry> updateUserPassword(@RequestBody UserEntry updatedUser, @PathVariable String username) {
        Optional<UserEntry> foundUser = userEntryService.findByUsername(username);
        if (foundUser.isPresent()) {
            UserEntry user = foundUser.get();
            user.setPassword(updatedUser.getPassword());
            userEntryService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update-username/{currentUsername}")
    public ResponseEntity<UserEntry> updateUsername(@PathVariable String currentUsername, @RequestBody UserEntry updatedUser) {
        Optional<UserEntry> foundUser = userEntryService.findByUsername(currentUsername);
        System.out.println(foundUser);
        if (foundUser.isPresent()) {
            UserEntry user = foundUser.get();
            user.setUsername(updatedUser.getUsername());
            userEntryService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
