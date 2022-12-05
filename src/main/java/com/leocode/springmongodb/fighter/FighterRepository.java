package com.leocode.springmongodb.fighter;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FighterRepository extends MongoRepository<Fighter, String> {
    Optional<Fighter> findFighterByName(String name);
}
