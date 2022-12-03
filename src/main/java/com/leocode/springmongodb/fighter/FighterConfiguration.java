package com.leocode.springmongodb.fighter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Configuration
public class FighterConfiguration {

    @Autowired
    FighterRepository fighterRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Bean
    CommandLineRunner runner() {
        return args -> {
            Date debut = new Date(2015, Calendar.DECEMBER,18);
            Biography biography = new Biography(
                    "Active",
                    "Queens, New York",
                    24,
                    "Synergy Fitness",
                    debut,
                    60.00,
                    209.4,
                    58.0,
                    50.0
            );
            String nickname = "El Fuerte";
            Fighter fighter = new Fighter(
                    "Leonel",
                    "Barrientos",
                    nickname,
                    "Middleweight Division",
                    "GOOD",
                    biography,
                    "https://avatars.githubusercontent.com/u/77355023?v=4"
            );

            fighterRepository.findFighterByNickname(nickname)
                    .ifPresentOrElse(f -> {
                        System.out.println(f + " already exists");
                    },() -> {
                        System.out.println("Inserting fighter " + fighter);
                        fighterRepository.insert(fighter);
                    });
        };
    }

    private void usingMongoTemplateAndQuery(FighterRepository fighterRepository, MongoTemplate mongoTemplate, Fighter fighter, String nickname){
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
}
