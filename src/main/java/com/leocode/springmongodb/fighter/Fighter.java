package com.leocode.springmongodb.fighter;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Fighter {
    @Id
    private String id;
    private String name;
    private String nickname;
    private String weightclass;
    private String record;
    private Biography biography;
    private String image;

    public Fighter(String name, String nickname, String weightclass, String record, Biography biography, String image) {
        this.name = name;
        this.nickname = nickname;
        this.weightclass = weightclass;
        this.record = record;
        this.biography = biography;
        this.image = image;
    }
}
