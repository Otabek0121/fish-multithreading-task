package com.example.fish2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Location {

    private Random random=new Random();
    private final  Integer aquarium_X = 10;
    private final  Integer aquarium_Y = 10;

    private Integer x = random.nextInt(aquarium_X);
    private Integer y = random.nextInt(aquarium_Y);

}
