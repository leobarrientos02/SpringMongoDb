package com.leocode.springmongodb.referee;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RefereeDTO {
    private String name;
    private String image;
    private Integer age;
}
