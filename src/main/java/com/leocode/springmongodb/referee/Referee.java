package com.leocode.springmongodb.referee;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Referee {
    @Id
    private String id;
    private String name;

    private String age;
    private String image;

    public Referee(String name, String age, String image) {
        this.name = name;
        this.age = age;
        this.image = image;
    }
}
