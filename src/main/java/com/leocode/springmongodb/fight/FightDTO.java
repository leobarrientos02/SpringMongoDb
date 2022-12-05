package com.leocode.springmongodb.fight;

import com.leocode.springmongodb.fighter.Fighter;
import com.leocode.springmongodb.referee.Referee;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class FightDTO {
    private String fighter_id_one;
    private String fighter_id_two;
    private String referee_id;
    private String fight_type;
    private String arena_name;
    private String arena_location;
    private LocalDate date;
    private String result;
}
