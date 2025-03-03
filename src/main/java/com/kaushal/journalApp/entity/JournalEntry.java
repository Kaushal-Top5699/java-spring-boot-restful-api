package com.kaushal.journalApp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter // Lombok Getter or can use @Data will cover both.
@Setter // Lombok Setter
@NoArgsConstructor
public class JournalEntry {

    @Id
    private String id;

    @NonNull
    private String title;
    private String content;


}
