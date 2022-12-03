package com.leocode.springmongodb.referee;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RefereeRepository extends MongoRepository<Referee, String> {
}
