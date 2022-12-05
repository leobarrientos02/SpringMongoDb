package com.leocode.springmongodb.fight;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/fights")
@AllArgsConstructor
public class FightController {

    private final FightService fightService;

    @GetMapping
    public List<Fight> fetchAllFights(){
        return fightService.getAllFights();
    }

    @GetMapping(path = "{id}")
    public Fight fetchFight(@PathVariable String id){
        return fightService.getFight(id);
    }

    @PostMapping
    public void addFight(@RequestBody FightDTO fightDTO){
        fightService.addFight(fightDTO);
    }

    @PutMapping(path = "{id}")
    public void updateFight(@PathVariable String id, @RequestBody FightDTO fightDTO){
        fightService.updateFight(id, fightDTO);
    }

    @DeleteMapping(path = "{id}")
    public void deleteFight(@PathVariable String id){
        fightService.deleteFight(id);
    }
}
