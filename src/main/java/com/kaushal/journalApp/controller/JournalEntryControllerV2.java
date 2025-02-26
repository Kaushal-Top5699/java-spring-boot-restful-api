package com.kaushal.journalApp.controller;

import com.kaushal.journalApp.entity.JournalEntry;
import com.kaushal.journalApp.entity.JournalEntryPostgres;
import com.kaushal.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    // For MongoDB
    @GetMapping("/get-all")
    public ResponseEntity<List<JournalEntry>> getAll() {
        if (!journalEntryService.getAllEnteries().isEmpty()) {
            return new ResponseEntity<>(journalEntryService.getAllEnteries(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/new-entry")
    public ResponseEntity<JournalEntry> creatNewEntry(@RequestBody JournalEntry journalEntry) {
        try {
            journalEntryService.createNewEntry(journalEntry);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<JournalEntry> getJournalById(@RequestParam String id) {
       Optional<JournalEntry> journalEntry  = journalEntryService.getJournalById(id);
       if (journalEntry.isPresent()) {
           return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
       }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<JournalEntry> deleteById(@PathVariable String id) {
        Optional<JournalEntry> deletedJournal =  journalEntryService.deleteById(id);
        if (deletedJournal.isPresent()) {
            return new ResponseEntity<>(deletedJournal.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<JournalEntry> updateById(@PathVariable String id, @RequestBody JournalEntry updatedJournal) {
        Optional<JournalEntry> update = journalEntryService.updateById(id, updatedJournal);
        if (update.isPresent()) {
            return new ResponseEntity<>(update.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // For MongoDB



    // For Postgres
    @GetMapping("/get-all-post")
    public List<JournalEntryPostgres> getAllPostgres() {
        return journalEntryService.getAllEntriesPostgres();
    }

    @PostMapping("/new-entry-post")
    public String creatNewEntryPostgres(@RequestBody JournalEntryPostgres journalEntry) {
        return journalEntryService.createNewEntryPostgres(journalEntry);
    }

    @GetMapping("/find-by-id-post")
    public Optional<JournalEntryPostgres> getJournalByIdPostgres(@RequestParam long id) {
        return journalEntryService.getJournalByIdPostgres(id);
    }

    @DeleteMapping("/delete-post/{id}")
    public boolean deleteByIdPostgres(@PathVariable long id) {
        return journalEntryService.deleteByIdPostgres(id);
    }

    @PutMapping("/update-post/{id}")
    public JournalEntryPostgres updateByIdPostgres(@PathVariable long id, @RequestBody JournalEntryPostgres updatedJournal) {
        System.out.println("Call on update route.");
        return journalEntryService.updateByIdPostgres(id, updatedJournal);
    }
    // For Postgres
}
