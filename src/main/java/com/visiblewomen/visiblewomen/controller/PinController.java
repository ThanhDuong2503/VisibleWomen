package com.visiblewomen.visiblewomen.controller;


import com.visiblewomen.visiblewomen.model.Pin;
import com.visiblewomen.visiblewomen.service.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class PinController {

    private final PinService pinService;

    @Autowired
    public PinController(PinService pinService) {
        this.pinService = pinService;
    }

    @GetMapping("/pins")
    ResponseEntity<List<Pin>> getPins() {

        List<Pin> pinsResponse = pinService.getAllPins();
        return pinsResponse != null ? ResponseEntity.ok(pinsResponse) : ResponseEntity.noContent().build();
    }

    @GetMapping("/pins/{id}")
    ResponseEntity<Pin> getPinById(@PathVariable String id) {
        Pin pinResponse = pinService.getPinById(id);
        return pinResponse != null ? ResponseEntity.ok(pinResponse) : ResponseEntity.noContent().build();
    }

    @PostMapping("/pins")
    ResponseEntity<Pin> addPin(@RequestBody Pin pinToAdd) {
        Pin pinResponse = pinService.addPin(pinToAdd);
        return pinResponse != null ? ResponseEntity.ok(pinResponse) : ResponseEntity.noContent().build();
    }

    @DeleteMapping("/pins/{id}")
    public void deletePin(@PathVariable String id) {
        pinService.deletePinById(id);
    }

}
