package com.leocode.springmongodb.fighter;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/fighters")
@AllArgsConstructor
public class FighterController {

    private final FighterService fighterService;

    @GetMapping
    public List<Fighter> fetchAllFighters() {
        return fighterService.getAllFighters();
    }

    @GetMapping(path = "{id}")
    public Fighter fetchFighter(@PathVariable String id) {
        return fighterService.getFighter(id);
    }

    @PostMapping
    public void addFighter(@RequestBody FighterDTO fighterDTO) {
        fighterService.addFighter(fighterDTO);
    }

    @PutMapping(path = "{id}")
    public void updateFighter(@PathVariable String id, @RequestBody FighterDTO fighterDTO){
        fighterService.updateFighter(id, fighterDTO);
    }

    @DeleteMapping(path = "{id}")
    public void deleteFighter(@PathVariable String id) {
        fighterService.deleteFighter(id);
    }
}
