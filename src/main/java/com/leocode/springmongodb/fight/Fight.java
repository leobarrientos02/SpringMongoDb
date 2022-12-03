package com.leocode.springmongodb.fight;

import com.leocode.springmongodb.referee.Referee;
import com.leocode.springmongodb.fighter.Fighter;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Fight {
    @Id
    private String id;
    private Fighter fighter1;
    private Fighter fighter2;
    private Referee referee;
    private String fight_type;
    private Arena arena;
    private Date date;
    private String result;

    public Fight(Fighter fighter1, Fighter fighter2, Referee referee, String fight_type, Arena arena, Date date) {
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
        this.referee = referee;
        this.fight_type = fight_type;
        this.arena = arena;
        this.date = date;
    }

    public Fight(Fighter fighter1, Fighter fighter2, Referee referee, String fight_type, Arena arena, Date date, String result) {
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
        this.referee = referee;
        this.fight_type = fight_type;
        this.arena = arena;
        this.date = date;
        this.result = result;
    }
}
