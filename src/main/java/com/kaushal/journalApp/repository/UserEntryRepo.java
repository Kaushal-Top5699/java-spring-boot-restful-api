package com.kaushal.journalApp.repository;

import com.kaushal.journalApp.entity.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserEntryRepo extends MongoRepository<UserEntry, ObjectId> {
    Optional<UserEntry> findByUsername(String username);
}
