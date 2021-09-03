package com.visiblewomen.visiblewomen.service;

import com.visiblewomen.visiblewomen.db.PinMongoDb;
import com.visiblewomen.visiblewomen.model.Pin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PinService {

    private final PinMongoDb pinMongoDb;

    @Autowired
    public PinService(PinMongoDb pinMongoDb) {
        this.pinMongoDb = pinMongoDb;
    }

    public List<Pin> getAllPins() {
        return pinMongoDb.findAll();
    }

    public Pin getPinById(String id) {
        Optional<Pin> optionalPin = pinMongoDb.findById(id);
        return optionalPin.orElseThrow(() -> new NoSuchElementException("Pin with ID:" + id + "not found."));
    }

    public Pin addPin(Pin pinToAdd) {
        return pinMongoDb.save(pinToAdd);
    }

    public void deletePinById (String id) {
        pinMongoDb.deleteById(id);
    }
}
