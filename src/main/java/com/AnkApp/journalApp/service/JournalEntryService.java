package com.AnkApp.journalApp.service;

import com.AnkApp.journalApp.entity.JournalEntry;
import com.AnkApp.journalApp.entity.User;
import com.AnkApp.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


    @Transactional
    public void saveJournal(JournalEntry journalEntry, String userName){
        try {
            User user = userService.findByUserName(userName);
            if(user != null) {
                journalEntry.setDate(LocalDateTime.now());
                JournalEntry saved = journalEntryRepository.save(journalEntry);
                user.getJournalEntries().add(saved);
                userService.saveUser(user);
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while saving the data", e);
        }
    }

    //for the sake of updating the Journal
    public void saveJournal(JournalEntry journalEntry){
        try {
                journalEntry.setDate(LocalDateTime.now());
                journalEntryRepository.save(journalEntry);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<JournalEntry> getJournals(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getJournalbyId(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deletebyId(ObjectId id, String userName){
        try {
            User user = userService.findByUserName(userName);
            boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
                return true;
            }
        }
        catch(Exception e){
            throw new RuntimeException("An Error occurred while trying to delete Journal", e);
        }
            return false;
    }

}
