package com.AnkApp.journalApp.controller;

import com.AnkApp.journalApp.entity.JournalEntry;
import com.AnkApp.journalApp.entity.User;
import com.AnkApp.journalApp.service.JournalEntryService;
import com.AnkApp.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;


    @GetMapping("getAll")
    public ResponseEntity<?> getAllJournalEnteriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> journals = user.getJournalEntries();
        if(journals != null && !journals.isEmpty()){
            return new ResponseEntity<>(journals, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getJournal/{id}")
    public ResponseEntity<?> getJournalbyId(@PathVariable ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.getJournalbyId(id);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Journal entry not found.", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("ID not mapped with same user.Please Check", HttpStatus.NOT_FOUND);
    }

    @PostMapping("create")
    public ResponseEntity<JournalEntry> createJournal(@RequestBody JournalEntry journal) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalEntryService.saveJournal(journal, userName);
            return new ResponseEntity<>(journal, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> DeleteJournal(@PathVariable ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        if(journalEntryService.deletebyId(id, userName)){
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateJournal(
            @PathVariable ObjectId id,
            @RequestBody JournalEntry newEntry
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());

        if(!collect.isEmpty()){
            JournalEntry old = journalEntryService.getJournalbyId(id).orElse(null);
            if(old != null){
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                old.setDate(LocalDateTime.now());
                journalEntryService.saveJournal(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

//Best Practise
//Controller --> Service --> Repository