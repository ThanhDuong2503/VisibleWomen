package com.visiblewomen.visiblewomen.db;

import com.visiblewomen.visiblewomen.model.Pin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PinMongoDb extends MongoRepository<Pin, String>{
    Optional<Pin> findById(final String id);
    List<Pin> findAll();
}
