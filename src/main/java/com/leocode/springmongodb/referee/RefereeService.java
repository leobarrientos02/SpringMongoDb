package com.leocode.springmongodb.referee;

import com.leocode.springmongodb.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RefereeService {

    private final RefereeRepository refereeRepository;

    public List<Referee> getAllReferees(){
        return refereeRepository.findAll();
    }

    public void addReferee(RefereeDTO refereeDTO) {
        Referee referee = new Referee(
                refereeDTO.getName(), refereeDTO.getAge(), refereeDTO.getImage()
        );
        refereeRepository.insert(referee);
    }

    public Referee getReferee(String id){
        return refereeRepository.findById(id)
                .orElseThrow(()->{
                    throw new NotFoundException("Referee with id:" + id + " not found.");
                });
    }

    public void updateReferee(String id, RefereeDTO refereeDTO){
        refereeRepository.findById(id)
                .ifPresentOrElse(r -> {
                    r.setName(refereeDTO.getName());
                    r.setAge(refereeDTO.getAge());
                    r.setImage(refereeDTO.getImage());
                    refereeRepository.save(r);
                }, () -> {
                    throw new NotFoundException("Referee with id:" + id + " not found.");
                });
    }

    public void deleteReferee(String id){
        refereeRepository.findById(id)
                .ifPresentOrElse(refereeRepository::delete, () -> {
                    throw new NotFoundException("Referee with id:" + id + " not found.");
                });
    }
}
