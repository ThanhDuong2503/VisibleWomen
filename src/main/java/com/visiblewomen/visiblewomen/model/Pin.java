package com.visiblewomen.visiblewomen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pin {
    @Id
    private String id;
    private String description;
    private double latitude;
    private double longitude;
    private String email;
    private HarassmentCategory harassmentCategory;
}
