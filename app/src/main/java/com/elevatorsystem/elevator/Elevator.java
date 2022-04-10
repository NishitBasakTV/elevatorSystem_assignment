package com.elevatorsystem.elevator;

import com.elevatorsystem.requests.Request;

import java.util.LinkedHashSet;

public class Elevator implements  ElevatorFeatures{
    public boolean fan = false;
    public boolean light = true;
    private int currentFloor = 0;
    private Direction currentDirection = Direction.UP;
    private State currentState = State.STOPPED;
    private final int MAX_FLOOR = 5;


    public void run(Request request) {
        System.out.println("Elevator is at floor - " + currentFloor);

        Direction direction = request.getExternalRequest().getDirectionToGo();
        int sourceFloor = request.getExternalRequest().getSourceFloor();
        int destinationFloor = request.getInternalRequest().getDestinationFloor();

        this.goToFloor(sourceFloor);
        this.goToFloor(destinationFloor);


    }


    private void goToFloor(int floorToGo) {

        if (currentFloor == floorToGo) {
            this.openDoor(currentFloor);
            this.closeDoor(currentFloor);
        } else if (currentFloor < floorToGo) {
            for (int i = currentFloor; i < floorToGo; i++) {
                this.moveUp();
                if (currentFloor == floorToGo) {
                    this.openDoor(currentFloor);
                    this.closeDoor(currentFloor);
                }
            }
        } else {
            for (int i = currentFloor; i > floorToGo; i--) {
                this.moveDown();
                if (currentFloor == floorToGo) {
                    this.openDoor(currentFloor);
                    this.closeDoor(currentFloor);
                }
            }
        }


    }


    private void moveUp() {
        if (currentFloor == MAX_FLOOR) {
            System.out.println("Elevator is on top floor. Cannot go up.");
            return;
        }
        currentDirection = Direction.UP;
        currentState = State.MOVING;
        System.out.println("Elevator is going up");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        currentFloor++;
        currentState = State.STOPPED;
        System.out.println("Reached to floor - " + currentFloor);
    }

    private void moveDown() {
        if (currentFloor == 0) {
            System.out.println("Elevator is on ground floor. Cannot go down.");
            return;
        }
        currentDirection = Direction.DOWN;
        currentState = State.MOVING;
        System.out.println("Elevator is going down");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        currentFloor--;
        currentState = State.STOPPED;
        System.out.println("Reached to floor - " + currentFloor);
    }

    public void openDoor(int floor) {
        System.out.println("Elevator door is opening at floor - " + floor + ".");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void closeDoor(int floor) {
        System.out.println("Elevator door is closing at floor - " + floor + ".");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void turnOnLight() {
        light = true;
        System.out.println("Elevator light is turned on");
    }

    @Override
    public void turnOffLight() {
        light = false;
        System.out.println("Elevator light is turned off");
    }

    @Override
    public void turnOnFan() {
        fan = true;
        System.out.println("Elevator fan is turned on");
    }

    @Override
    public void turnOffFan() {
        fan = false;
        System.out.println("Elevator fan is turned off");
    }

}
