package com.leocode.springmongodb.fight;

import com.leocode.springmongodb.exceptions.NotFoundException;
import com.leocode.springmongodb.fighter.Fighter;
import com.leocode.springmongodb.fighter.FighterService;
import com.leocode.springmongodb.referee.Referee;
import com.leocode.springmongodb.referee.RefereeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class FightService {
    private final FightRepository fightRepository;

    private final FighterService fighterService;

    private final RefereeService refereeService;

    private static final Logger log = LoggerFactory.getLogger(FightService.class);
    public List<Fight> getAllFights(){
        log.info("Fetching all fights from the database.");
        return fightRepository.findAll();
    }

    public Fight getFight(String id){
        return fightRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            "Fight with id" + id + " was not found."
                    );
                });
    }

    public void addFight(FightDTO fightDTO){
        Fighter fighter1 = fighterService.getFighter(fightDTO.getFighter_id_one());
        Fighter fighter2 = fighterService.getFighter(fightDTO.getFighter_id_two());
        Referee referee = refereeService.getReferee(fightDTO.getReferee_id());
        Arena arena = new Arena(fightDTO.getArena_name(), fightDTO.getArena_location());
        LocalDate date = fightDTO.getDate();
        Fight fight = new Fight(
                fighter1, fighter2, referee, fightDTO.getFight_type(), arena, date
        );

        log.info(fighter1.getName() + " vs " + fighter2.getName() + " has been added.");
        fightRepository.insert(fight);
    }

    public void updateFight(String id, FightDTO fightDTO){
        fightRepository.findById(id)
                .ifPresentOrElse(fight -> {
                    Fighter fighter1 = fighterService.getFighter(fightDTO.getFighter_id_one());
                    Fighter fighter2 = fighterService.getFighter(fightDTO.getFighter_id_two());
                    Referee referee = refereeService.getReferee(fightDTO.getReferee_id());
                    Arena arena = new Arena(fightDTO.getArena_name(), fightDTO.getArena_location());
                    fight.setFighter1(fighter1);
                    fight.setFighter2(fighter2);
                    fight.setReferee(referee);
                    fight.setFight_type(fightDTO.getFight_type());
                    fight.setArena(arena);
                    LocalDate date = fightDTO.getDate();
                    fight.setDate(date);
                    log.info(fighter1.getName() + " vs " + fighter2.getName() + " has been updated.");
                    fightRepository.save(fight);
                }, () -> {
                    throw new NotFoundException("Fight with id:" + id + " not found.");
                });
    }

    public void deleteFight(String id){
        fightRepository.findById(id)
                .ifPresentOrElse(fight -> {
                    log.info(fight.getFighter1().getName() + " vs " + fight.getFighter2().getName() + " has deleted.");
                    fightRepository.delete(fight);
                }, () -> {
                    throw new NotFoundException("Fight with id:" + id + " not found.");
                });
    }
}