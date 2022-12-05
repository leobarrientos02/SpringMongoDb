package com.leocode.springmongodb.referee;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface RefereeRepository extends MongoRepository<Referee, String> {
    Optional<Referee> findRefereeByName(String name);
}
