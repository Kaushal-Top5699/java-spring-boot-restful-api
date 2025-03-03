package com.kaushal.journalApp.service;

import com.kaushal.journalApp.entity.JournalEntry;
import com.kaushal.journalApp.entity.JournalEntryPostgres;
import com.kaushal.journalApp.entity.UserEntry;
import com.kaushal.journalApp.repository.JournalEntryPostgresRepo;
import com.kaushal.journalApp.repository.JournalEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserEntryService userEntryService;

    @Autowired
    private JournalEntryPostgresRepo journalEntryRepoPostgress;

    // For MongoDB
    public List<JournalEntry> getAllEnteries() {
        return journalEntryRepo.findAll();
    }

    // For Postgres
    public List<JournalEntryPostgres> getAllEntriesPostgres() {
        return journalEntryRepoPostgress.findAll();
    }

    // For MongoDB
    public Optional<JournalEntry> getJournalById(String id) {
        return journalEntryRepo.findById(id);
    }

    // For Postgres
    public Optional<JournalEntryPostgres> getJournalByIdPostgres(long id) {
        return journalEntryRepoPostgress.findById(id);
    }

    // For MongoDB
    public String createNewEntry(JournalEntry journalEntry, String username) {
        Optional<UserEntry> userFound = userEntryService.findByUsername(username);
        JournalEntry saved = journalEntryRepo.save(journalEntry);
        if (userFound.isPresent()) {
            UserEntry user = userFound.get();
            user.getJournalEntries().add(saved);
            userEntryService.saveUser(user);
            return "Saved!";
        }
        return "Failed";
    }

    // For Postgres
    public String createNewEntryPostgres(JournalEntryPostgres journalEntry) {
        journalEntryRepoPostgress.save(journalEntry);
        return "Saved!";
    }

    // For MongoDB
    public Optional<JournalEntry> deleteById(String id) {
        if (this.getJournalById(id).isPresent()) {
            Optional<JournalEntry> foundJournal = journalEntryRepo.findById(id);
            journalEntryRepo.deleteById(id);
            return foundJournal;
        }
        return Optional.empty();
    }

    // For Postgres
    public boolean deleteByIdPostgres(long id) {
        if (this.getJournalByIdPostgres(id).isPresent()) {
            journalEntryRepoPostgress.deleteById(id);
            return true;
        }
        return false;
    }

    // For MongoDB
    public Optional<JournalEntry> updateById(String id, JournalEntry updatedJournal) {
        if (this.getJournalById(id).isPresent()) {
            this.getJournalById(id).map(currentJournal -> {
                currentJournal.setTitle(updatedJournal.getTitle());
                currentJournal.setContent(updatedJournal.getContent());
                return journalEntryRepo.save(currentJournal);
            });
        }
        return Optional.empty();
    }

    // For Postgres
    public JournalEntryPostgres updateByIdPostgres(long id, JournalEntryPostgres updatedJournal) {
        return journalEntryRepoPostgress.findById(id).map(currentJournal -> {
            currentJournal.setTitle(updatedJournal.getTitle());
            currentJournal.setContent(updatedJournal.getContent());
            return journalEntryRepoPostgress.save(currentJournal);
        }).orElse(null);
    }

}
