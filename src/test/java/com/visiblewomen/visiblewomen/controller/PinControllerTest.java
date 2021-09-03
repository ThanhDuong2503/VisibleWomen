package com.visiblewomen.visiblewomen.controller;

import com.visiblewomen.visiblewomen.db.PinMongoDb;
import com.visiblewomen.visiblewomen.model.Pin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PinControllerTest {

    @LocalServerPort
    public int port;

    @Autowired
    public TestRestTemplate restTemplate;

    @Autowired
    public PinMongoDb pinMongoDb;

    @BeforeEach
    public void resetDb() {
        pinMongoDb.deleteAll();
    }

    @Test
    @DisplayName("should return all Pins in Database")
    public void getPins() {
        // GIVEN
        String url = "http://localhost:" + port + "pins";
        LocalDateTime localDateTime = LocalDateTime.now();

        pinMongoDb.save(new Pin("1", "test1", 50.10, 12.30, "test@web.de", localDateTime, Pin.HarassmentCategory.RACE));
        pinMongoDb.save(new Pin("2", "test2", 51.10, 13.30, "test2@web.de", localDateTime, Pin.HarassmentCategory.RELIGION));
        pinMongoDb.save(new Pin("3", "test3", 52.10, 14.30, "test3@web.de", localDateTime, Pin.HarassmentCategory.SEXUAL));

        // WHEN
        HttpEntity entity = new HttpEntity(new HttpHeaders());
        ResponseEntity<Pin[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Pin[].class);
        HttpStatus statusCode = response.getStatusCode();
        Pin[] pins = response.getBody();

        // THEN
        assertEquals(HttpStatus.OK, statusCode);
        assert pins != null;
        assertEquals(3, pins.length);
        assertEquals(new Pin("1", "test1", 50.10, 12.30, "test@web.de", localDateTime, Pin.HarassmentCategory.RACE), pins[0]);
        assertEquals(new Pin("2", "test2", 51.10, 13.30, "test2@web.de", localDateTime, Pin.HarassmentCategory.RELIGION), pins[1]);
        assertEquals(new Pin("3", "test3", 52.10, 14.30, "test3@web.de", localDateTime, Pin.HarassmentCategory.SEXUAL), pins[2]);

    }

    @Test
    @DisplayName("should return a Pin with the given Id")
    public void getPinById() {
        // GIVEN
        String url = "http://localhost:" + port + "pins/1";
        LocalDateTime localDateTime = LocalDateTime.now();

        pinMongoDb.save(new Pin("1", "test1", 50.10, 12.30, "test@web.de", localDateTime, Pin.HarassmentCategory.RACE));
        pinMongoDb.save(new Pin("2", "test2", 51.10, 13.30, "test2@web.de", localDateTime, Pin.HarassmentCategory.RELIGION));
        pinMongoDb.save(new Pin("3", "test3", 52.10, 14.30, "test3@web.de", localDateTime, Pin.HarassmentCategory.SEXUAL));

        // WHEN
        HttpEntity entity = new HttpEntity(new HttpHeaders());
        ResponseEntity<Pin> response = restTemplate.exchange(url, HttpMethod.GET, entity, Pin.class);

        // THEN
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), new Pin("1", "test1", 50.10, 12.30, "test@web.de", localDateTime, Pin.HarassmentCategory.RACE));
    }

    @Test
    @DisplayName("should add a Pin to the database")
    public void addPin() {
        LocalDateTime localDateTime = LocalDateTime.now();

        // GIVEN
        String url = "http://localhost:" + port + "pins";
        HttpEntity<Pin> requestEntity = new HttpEntity<>(new Pin("1", "test1", 50.10, 12.30, "test@web.de", localDateTime, Pin.HarassmentCategory.RACE));

        //WHEN
        ResponseEntity<Pin> postResponse = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Pin.class);

        //THEN
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        assertEquals(new Pin("1", "test1", 50.10, 12.30, "test@web.de", localDateTime, Pin.HarassmentCategory.RACE), postResponse.getBody());
        assertTrue(pinMongoDb.findAll().contains(new Pin("1", "test1", 50.10, 12.30, "test@web.de", localDateTime, Pin.HarassmentCategory.RACE)));
    }

    @Test
    @DisplayName("should delete a Pin with the given Id")
    public void deletePin() {
        // GIVEN
        String url = "http://localhost:" + port + "pins/2";
        LocalDateTime localDateTime = LocalDateTime.now();

        pinMongoDb.save(new Pin("1", "test1", 50.10, 12.30, "test@web.de", localDateTime, Pin.HarassmentCategory.RACE));
        pinMongoDb.save(new Pin("2", "test2", 51.10, 13.30, "test2@web.de", localDateTime, Pin.HarassmentCategory.RELIGION));
        pinMongoDb.save(new Pin("3", "test3", 52.10, 14.30, "test3@web.de", localDateTime, Pin.HarassmentCategory.SEXUAL));

        // WHEN
        HttpEntity entity = new HttpEntity(new HttpHeaders());
        restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);

        // THEN
        assertTrue(pinMongoDb.findById("2").isEmpty());
    }
}