package com.visiblewomen.visiblewomen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pin")
public class Pin {
    @Id
    private String id;
    private String description;
    private double latitude;
    private double longitude;
    private String email;
    private LocalDateTime time;
    private HarassmentCategory harassmentCategory;

    @Getter
    public enum HarassmentCategory {
        RACE,
        GENDER,
        RELIGION,
        DISABILITY,
        SEXUAL_ORIENTATION,
        AGE_RELATED,
        SEXUAL
    }
}
