package com.example.fish2;

import lombok.Getter;
import lombok.SneakyThrows;


import java.util.Objects;
import java.util.UUID;

import static com.example.fish2.Main.random;


public class Fish extends Thread {

    private final static Integer aquarium_X = 5;
    private final static Integer aquarium_Y = 5;
    private static int move;

    private UUID id = UUID.randomUUID();
    private String name;


    private Long lifeTime = System.currentTimeMillis() + random.nextInt(20_000);

    @Getter
    private boolean male = random.nextBoolean();

    private final Location location=new Location();

    public Fish() {
        name = super.getName();
    }

    @SneakyThrows
    @Override
    public void run() {

        super.run();
        int count=0;
        while (!Thread.currentThread().isInterrupted()) {
            Thread.sleep(100L);

            if(count==0){
                checkDead();
                count++;
            }
            editLocation();
            checkDead();
        }
    }

    private void checkDead() {

        try {
            if (!Thread.currentThread().isInterrupted())

                if (System.currentTimeMillis() > lifeTime) {

                    Main.fishes.remove(this);
                    System.err.println("Killed " + name);

                    Thread.currentThread().interrupt();
                }

        } catch (Exception e) {

        }
    }

    private void editLocation() {

//       int x = location.getX();
//       int y = location.getY();

        // 0 - chapga
        // 1 - o`ngga
        // 2 - tepagaga
        // 3 - pastga

        move=random.nextInt(4);

      //  System.out.println("move "+ move);


        if(move==0){
            if (location.getX()!=0) {
                  location.setX(location.getX()-1);
                location.setY(location.getY());
            }
        }

       else if(move==1){
            if (!Objects.equals(location.getX(), aquarium_X-1)) {
                location.setX(location.getX()+1);
                location.setY(location.getY());
            }
        }

        else if(move==2){
            if (!Objects.equals(location.getY(), aquarium_Y-1)) {
                location.setY(location.getY()+1);
                location.setX(location.getX());
            }

        }

        else {
            if (location.getY()!=0) {
                location.setX(location.getX());
                location.setY(location.getY()-1);
            }
        }


    }

    public String getLocation() {
        return location.getX() + "_" + location.getY();
    }

    @Override
    public String toString() {
        return "Fish{" +
               "name='" + name + '\'' +
               ", lifeTime=" + lifeTime +
               ", male=" + male +
               ", x=" + location.getX() +
               ", y=" + location.getY() +
               ", move=" + move +
               '}'+"\n";
    }


}