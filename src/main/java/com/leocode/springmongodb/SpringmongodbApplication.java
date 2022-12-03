package com.leocode.springmongodb;

import com.leocode.springmongodb.fighter.Biography;
import com.leocode.springmongodb.fighter.Fighter;
import com.leocode.springmongodb.fighter.FighterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringmongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringmongodbApplication.class, args);
	}

}
