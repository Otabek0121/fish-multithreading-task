package com.example.fish2;

import lombok.SneakyThrows;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static Random random = new Random();
    public static List<Fish> fishes = Collections.synchronizedList(new ArrayList<>());
    public static Scanner scanner = new Scanner(System.in);

    static Map<String, ConcurrentHashMap<Boolean, Integer>> meetingFishes = new ConcurrentHashMap<>();

    @SneakyThrows
    public static void main(String[] args) {

        System.err.print("Enter aquarium fishes size: ");
        int aquariumSize = scanner.nextInt();


        System.err.print("Enter Fishes size: ");
        int fishSize = scanner.nextInt();


        for (int i = 0; i < fishSize; i++) {

            Fish fish = new Fish();
            fish.start();

            fishes.add(fish);

        }

        while (fishes.size() >= 2) {

            System.out.println(fishes);
            Thread.sleep(1000L);

            System.out.println(fishes.size());

            methods(aquariumSize);
        }

    }

    private static void methods(int aquariumSize) {

        findMeetingFishes();

        kopayish(aquariumSize);


    }

    private static void kopayish(int aquariumSize) {


        Collection<ConcurrentHashMap<Boolean, Integer>> values = meetingFishes.values();
        for (ConcurrentHashMap<Boolean, Integer> value : values) {
            Integer male = value.getOrDefault(true, 0);
            Integer female = value.getOrDefault(false, 0);
            int newFishesCount = Math.min(male, female);

            if(fishes.size()<aquariumSize){
                for (int i = 0; i < newFishesCount; i++) {
                    Fish fish = new Fish();
                    fish.start();
                    System.out.println(" Fish was born  "+fish);
                    fishes.add(fish);
                }
            }


        }

    }

    private static void findMeetingFishes() {

        Map<String, ConcurrentHashMap<Boolean, Integer>> map = new ConcurrentHashMap<>();

        for (Fish fish : fishes) {
            ConcurrentHashMap<Boolean, Integer> orDefault = map.getOrDefault(fish.getLocation(), new ConcurrentHashMap<>());
            orDefault.put(fish.isMale(), orDefault.getOrDefault(fish.isMale(), 0) + 1);
            map.put(fish.getLocation(), orDefault);
        }

        meetingFishes = map;

    }

}