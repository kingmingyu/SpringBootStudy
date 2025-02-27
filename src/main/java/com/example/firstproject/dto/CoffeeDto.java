package com.example.firstproject.dto;

import com.example.firstproject.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class CoffeeDto {
    private Long id;
    private String name;
    private Integer price;

    public Coffee toEntity() {
        return new Coffee(id, name, price);
    }
}
