package com.AnkApp.journalApp.entity;

public class CreateJournalResponse {
    private String message;
    private JournalEntry journalEntry;

    public CreateJournalResponse(String message, JournalEntry journalEntry) {
        this.message = message;
        this.journalEntry = journalEntry;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JournalEntry getJournalEntry() {
        return journalEntry;
    }

    public void setJournalEntry(JournalEntry journalEntry) {
        this.journalEntry = journalEntry;
    }
}
