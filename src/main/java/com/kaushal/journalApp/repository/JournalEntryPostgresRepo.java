package com.kaushal.journalApp.repository;

import com.kaushal.journalApp.entity.JournalEntryPostgres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryPostgresRepo extends JpaRepository<JournalEntryPostgres, Long> {
}
