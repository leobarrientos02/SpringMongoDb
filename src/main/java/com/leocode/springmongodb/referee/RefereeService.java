package com.leocode.springmongodb.referee;

import com.leocode.springmongodb.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class RefereeService {

    private final RefereeRepository refereeRepository;

    private static final Logger log = LoggerFactory.getLogger(RefereeService.class);

    public List<Referee> getAllReferees(){
        log.info("Fetching all referees from the database");
        return refereeRepository.findAll();
    }

    public void addReferee(RefereeDTO refereeDTO) {
        String name = refereeDTO.getName();
        refereeRepository.findRefereeByName(name)
                .ifPresentOrElse(r -> log.error(r + " already exist in the database."),() -> {
                    Referee referee = new Referee(
                            refereeDTO.getName(), refereeDTO.getAge(), refereeDTO.getImage()
                    );
                    log.info("Inserting " + referee.getName() + " to the database.");
                    refereeRepository.insert(referee);
                });

    }

    public Referee getReferee(String id){
        return refereeRepository.findById(id)
                .orElseThrow(()->{
                    throw new NotFoundException("Referee with id:" + id + " not found.");
                });
    }

    public void updateReferee(String id, RefereeDTO refereeDTO){
        refereeRepository.findById(id)
                .ifPresentOrElse(referee -> {
                    referee.setName(refereeDTO.getName());
                    referee.setAge(refereeDTO.getAge());
                    referee.setImage(refereeDTO.getImage());
                    log.info(referee.getName() + " has has been updated.");
                    refereeRepository.save(referee);
                }, () -> {
                    throw new NotFoundException("Referee with id:" + id + " not found.");
                });
    }

    public void deleteReferee(String id){
        refereeRepository.findById(id)
                .ifPresentOrElse(referee -> {
                    log.info(referee.getName() + " has been deleted.");
                    refereeRepository.delete(referee);
                    }, () -> {
                        throw new NotFoundException("Referee with id:" + id + " not found.");
                });
    }
}
