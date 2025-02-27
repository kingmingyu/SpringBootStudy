package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CoffeeApiController {
    @Autowired
    private CoffeeService coffeeService;

    //1. GET
    @GetMapping("/api/coffees")
    public List<Coffee> index(){
        return coffeeService.index();
    }
    @GetMapping("/api/coffees/{id}")
    public Coffee show(@PathVariable Long id){
        return coffeeService.show(id);
    }
    //2. POST
    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeDto coffeeDto){
        Coffee coffee = coffeeService.create(coffeeDto);
        return coffee != null ?
                ResponseEntity.status(HttpStatus.OK).body(coffee):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    //3.PATCH
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeDto dto){
        Coffee coffee = coffeeService.update(id, dto);
        return coffee != null ?
                ResponseEntity.status(HttpStatus.OK).body(coffee):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    //4. DELETE
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        Coffee coffee = coffeeService.delete(id);
        return coffee != null ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
