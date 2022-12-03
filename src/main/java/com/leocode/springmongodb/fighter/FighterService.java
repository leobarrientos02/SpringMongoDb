package com.leocode.springmongodb.fighter;

import com.leocode.springmongodb.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FighterService {

    private final FighterRepository fighterRepository;

    public List<Fighter> getAllFighters(){
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
        String nickname = fighterDTO.getNickname();
        Fighter fighter = new Fighter(
                fighterDTO.getName(),
                nickname,
                fighterDTO.getWeightclass(),
                fighterDTO.getRecord(),
                biography,
                fighterDTO.getImage()
        );
        fighterRepository.findFighterByNickname(nickname)
                .ifPresentOrElse(f -> System.out.println(f + " already exists"),() -> {
                    System.out.println("Inserting fighter " + fighter);
                    fighterRepository.insert(fighter);
                });
    }

    public Fighter getFighter(String id) {
        return fighterRepository.findById(id)
                .orElseThrow(()->{
                    throw new NotFoundException(
                            "Fighter with id:" + id + " not found.");
                });
    }

    public Fighter findFighterByNickname(String nickname) {
        return fighterRepository.findFighterByNickname(nickname)
                .orElseThrow(()->{
                    throw new NotFoundException("Fighter not found.");
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
                    fighterRepository.save(f);
                }, () -> {
                    throw new NotFoundException("Fighter with id:" + id + " not found.");
                });
    }

    public void deleteFighter(String id) {
        fighterRepository.findById(id)
                .ifPresentOrElse(fighterRepository::delete, () -> {
                    throw new NotFoundException("Fighter with id:" + id + " not found.");
                });
    }

//    private void usingMongoTemplateAndQuery(FighterRepository fighter_Repository, MongoTemplate mongo_Template, Fighter fighter, String nickname){
//        Query query = new Query();
//        query.addCriteria(Criteria.where("nickname").is(nickname));
//
//        List<Fighter> fighters = mongoTemplate.find(query, Fighter.class);
//        if(fighters.size() > 1) {
//            throw new IllegalStateException("Found other fighters with '" + nickname + "' nickname");
//        }
//
//        if(fighters.isEmpty()){
//            System.out.println("Inserting fighter " + fighter);
//            fighterRepository.insert(fighter);
//        }else{
//            System.out.println(fighter + " already exists");
//        }
//    }

}
