package com.leocode.springmongodb.fighter;

import com.leocode.springmongodb.fighter.Fighter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FighterRepository extends MongoRepository<Fighter, String> {
    Optional<Fighter> findFighterByNickname(String nickname);
}
