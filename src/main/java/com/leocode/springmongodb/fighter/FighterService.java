package com.leocode.springmongodb.fighter;

import com.leocode.springmongodb.exceptions.NotFoundException;
import com.leocode.springmongodb.utils.ExcelUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class FighterService {

    private final FighterRepository fighterRepository;

    private static final Logger log = LoggerFactory.getLogger(FighterService.class);

    public List<Fighter> getAllFighters(){
        log.info("Fetching all fighters from the database.");
        return fighterRepository.findAll();
    }
    public void addFighter(FighterDTO fighterDTO){
        Biography biography = new Biography(
                fighterDTO.getStatus(),
                fighterDTO.getBirthplace(),
                fighterDTO.getAge(),
                fighterDTO.getTraining(),
                fighterDTO.getDebut(),
                fighterDTO.getHeight(),
                fighterDTO.getWeight(),
                fighterDTO.getArm_reach(),
                fighterDTO.getLeg_reach()
        );
        String name = fighterDTO.getName();
        Fighter fighter = new Fighter(
                name,
                fighterDTO.getNickname(),
                fighterDTO.getWeightclass(),
                fighterDTO.getRecord(),
                biography,
                fighterDTO.getImage()
        );
        fighterRepository.findFighterByName(name)
                .ifPresentOrElse(f -> log.error(f + " already exists."),() -> {
                    log.info("Inserting " + fighter.getName() + " to the database.");
                    fighterRepository.insert(fighter);
                });
    }

    public Fighter getFighter(String id) {
        return fighterRepository.findById(id)
                .orElseThrow(()->{
                    throw new NotFoundException("Fighter with id:" + id + " not found.");
                });
    }

    public void updateFighter(String id, FighterDTO fighterDTO){
        fighterRepository.findById(id)
                .ifPresentOrElse(f -> {
                    f.setName(fighterDTO.getName());
                    f.setNickname(fighterDTO.getNickname());
                    f.setWeightclass(fighterDTO.getWeightclass());
                    f.setRecord(fighterDTO.getRecord());
                    Biography biography = new Biography(
                            fighterDTO.getStatus(),
                            fighterDTO.getBirthplace(),
                            fighterDTO.getAge(),
                            fighterDTO.getTraining(),
                            fighterDTO.getDebut(),
                            fighterDTO.getHeight(),
                            fighterDTO.getWeight(),
                            fighterDTO.getArm_reach(),
                            fighterDTO.getLeg_reach()
                    );
                    f.setBiography(biography);
                    f.setImage(fighterDTO.getImage());
                    log.info(f.getName() + " has been updated.");
                    fighterRepository.save(f);
                }, () -> {
                    throw new NotFoundException("Fighter with id:" + id + " not found.");
                });
    }

    public void deleteFighter(String id) {
        fighterRepository.findById(id)
                .ifPresentOrElse(fighter -> {
                   log.info(fighter.getName() + " has been deleted.");
                   fighterRepository.delete(fighter);
                }, () -> {
                    throw new NotFoundException("Fighter with id:" + id + " not found.");
                });
    }

    public void addUfcFighter(String name) throws IOException {
        ExcelUtil excelUtil = new ExcelUtil();
        Fighter fighter = excelUtil.getFighterDataFromExcel(name);
        fighterRepository.findFighterByName(name)
                .ifPresentOrElse(f -> {
                    throw new NotFoundException(f.getName() + " fighter already exists.");
                } ,() -> {
                    log.info("Inserting " + fighter.getName() + " to the database.");
                    fighterRepository.insert(fighter);
                });
    }
}
/*
    private void usingMongoTemplateAndQuery(FighterRepository fighter_Repository, MongoTemplate mongo_Template, Fighter fighter, String nickname){
        Query query = new Query();
        query.addCriteria(Criteria.where("nickname").is(nickname));

        List<Fighter> fighters = mongoTemplate.find(query, Fighter.class);
        if(fighters.size() > 1) {
            throw new IllegalStateException("Found other fighters with '" + nickname + "' nickname");
        }

        if(fighters.isEmpty()){
            System.out.println("Inserting fighter " + fighter);
            fighterRepository.insert(fighter);
        }else{
            System.out.println(fighter + " already exists");
        }
    }
*/