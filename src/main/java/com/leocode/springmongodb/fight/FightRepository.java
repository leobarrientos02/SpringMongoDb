package com.leocode.springmongodb.fight;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FightRepository extends MongoRepository<Fight, String> {
}
