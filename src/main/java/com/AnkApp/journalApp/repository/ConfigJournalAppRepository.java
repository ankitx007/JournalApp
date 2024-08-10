package com.AnkApp.journalApp.repository;

import com.AnkApp.journalApp.entity.ConfigJournalAppEntity;
import com.AnkApp.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}
