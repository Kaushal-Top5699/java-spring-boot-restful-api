package com.kaushal.journalApp.service;

import com.kaushal.journalApp.entity.UserEntry;
import com.kaushal.journalApp.repository.UserEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntryService {

    @Autowired
    private UserEntryRepo userEntryRepo;

    public List<UserEntry> getAllUsers() {
        return userEntryRepo.findAll();
    }

    public UserEntry saveUser(UserEntry userEntry) {
        userEntryRepo.save(userEntry);
        return userEntry;
    }

    public Optional<UserEntry> findById(ObjectId id) {
        return userEntryRepo.findById(id);
    }

    public Optional<UserEntry> deleteById(ObjectId id) {
        if (userEntryRepo.findById(id).isPresent()) {
            Optional<UserEntry> deletedUser = userEntryRepo.findById(id);
            userEntryRepo.deleteById(id);
            return deletedUser;
        }
        return Optional.empty();
    }

    public Optional<UserEntry> findByUsername(String username) {
        return userEntryRepo.findByUsername(username);
    }




}
