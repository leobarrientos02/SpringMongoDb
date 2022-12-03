package com.leocode.springmongodb.fighter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class FighterDTO {
    private String name;
    private String nickname;
    private String weightclass;
    private String record;
    private String status;
    private String birthplace;
    private Integer age;
    private String training;
    private LocalDate debut;
    private Double height;
    private Double weight;
    private Double arm_reach;
    private Double leg_reach;
    private String image;
}
