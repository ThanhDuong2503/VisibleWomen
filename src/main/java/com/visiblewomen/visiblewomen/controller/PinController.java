package com.visiblewomen.visiblewomen.controller;


import com.visiblewomen.visiblewomen.model.Pin;
import com.visiblewomen.visiblewomen.service.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pins")
public class PinController {

    private final PinService pinService;

    @Autowired
    public PinController(PinService pinService) {
        this.pinService = pinService;
    }

    @GetMapping
    public List<Pin> getPins() {
        return pinService.getAllPins();
    }

    @GetMapping("{id}")
    public Pin getPinById(@PathVariable String id) {
        return pinService.getPinById(id);
    }

    @PostMapping
    public Pin addPin(@RequestBody Pin pinToAdd) {
        pinService.addPin(pinToAdd);
        return pinToAdd;
    }

    @DeleteMapping("{id}")
    public void deletePin(@PathVariable String id) {
        pinService.deletePinById(id);
    }

}
