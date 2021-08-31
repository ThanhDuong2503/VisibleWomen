package com.visiblewomen.visiblewomen.db;

import com.visiblewomen.visiblewomen.model.Pin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PinMongoDb extends MongoRepository<Pin, String>{
}
