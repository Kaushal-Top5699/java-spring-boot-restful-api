package com.kaushal.journalApp.controller;

import com.kaushal.journalApp.entity.JournalEntry;
import com.kaushal.journalApp.entity.JournalEntryPostgres;
import com.kaushal.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    // For MongoDB
    @GetMapping("/get-all")
    public List<JournalEntry> getAll() {
        return journalEntryService.getAllEnteries();
    }

    @PostMapping("/new-entry")
    public String creatNewEntry(@RequestBody JournalEntry journalEntry) {
        return journalEntryService.createNewEntry(journalEntry);
    }

    @GetMapping("/get-by")
    public Optional<JournalEntry> getJournalById(@RequestParam String id) {
        return journalEntryService.getJournalById(id);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteById(@PathVariable String id) {
        return journalEntryService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public JournalEntry updateById(@PathVariable String id, @RequestBody JournalEntry updatedJournal) {
        System.out.println("Call on update route.");
        return journalEntryService.updateById(id, updatedJournal);
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

    @GetMapping("/get-by-post")
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
