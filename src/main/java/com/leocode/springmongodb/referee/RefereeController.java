package com.leocode.springmongodb.referee;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/referees")
@AllArgsConstructor
public class RefereeController {

    private final RefereeService refereeService;

    @GetMapping
    public List<Referee> fetchAllReferees(){
        return refereeService.getAllReferees();
    }

    @GetMapping(path = "{id}")
    public Referee fetchReferee(@PathVariable String id){
        return refereeService.getReferee(id);
    }

    @PostMapping
    public void addReferee(@RequestBody RefereeDTO refereeDTO){
        refereeService.addReferee(refereeDTO);
    }

    @PutMapping(path = "{id}")
    public void updateReferee(@PathVariable String id, @RequestBody RefereeDTO refereeDTO){
        refereeService.updateReferee(id, refereeDTO);
    }

    @DeleteMapping(path = "{id}")
    public void deleteReferee(@PathVariable String id){
        refereeService.deleteReferee(id);
    }
}
