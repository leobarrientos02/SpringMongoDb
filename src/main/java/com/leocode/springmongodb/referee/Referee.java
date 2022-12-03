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
    private String image;
    private String age;

    public Referee(String name, String image, String age) {
        this.name = name;
        this.image = image;
        this.age = age;
    }
}
