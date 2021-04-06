package elevator;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Elevator class handles the whole elevator logic
 */
public class Elevator {

    //current people quantity in the elevator
    private int currentPeopleCount = 0;

    //current elevator movement direction. By default - UP
    private String direction = "UP";

    //current elevator floor. By default - first floor
    private int currentFloor = 1;

    //count of all floors in the building
    private int maxFloor = Generator.getMaxFloors();

    private final Building building;

    //set of persons in the elevator
    private final Set<Person> peopleInElevator = new HashSet<>(5);

    public Elevator(Building building) {
        this.building = building;
    }

    /**
     * Main method what handles elevator logic.
     */
    public void startElevator() {
        Set<Person> people;

        //see checkPeople() method
        while (checkPeople(building.getFloors(), currentPeopleCount)) {

            //getting the current floor on which the elevator is located
            Floor floor = building.getFloors().stream().filter(e -> e.getNumber() == currentFloor).findFirst().get();

            //moves the elevator up and increments floor after the move
            if (direction.equals("UP")) {
                people = handleFloor("UP", floor);

                //checks for a last floor and changes movement direction
                if (currentFloor == maxFloor) {
                    moveElevatorIfOnLastFloor("DOWN", floor, people);
                    continue;
                }
                moveElevator(floor, people);
                currentFloor++;
            }
            //moves the elevator down and decrements floor after the move
            else {
                people = handleFloor("DOWN", floor);

                //checks for a first floor and changes movement direction
                if (currentFloor == 1) {
                    moveElevatorIfOnLastFloor("UP", floor, people);
                    continue;
                }
                moveElevator(floor, people);
                currentFloor--;
            }
        }
    }

    /**
     * Elevator moves while there are people inside it or there are people who didn't reach the needed floor
     * @param floors current floor
     * @param currentPeopleCount current people inside of elevator
     * @return true if there are people, false if there are no people inside of an elevator or not everybody reached the needed floor
     */
    private boolean checkPeople(List<Floor> floors, int currentPeopleCount) {
        int result = 0;
        for (Floor floor : floors) {
            result += floor.getPeopleOnTheFloor().stream().filter(e -> e.getCurrentFloor() != e.getNextFloor()).count();
        }
        return result > 0 || currentPeopleCount > 0;
    }

    /**
     * Elevator field maxFloor depends on person's last floor.
     * Field maxFloor recalculates after every loading or unloading people to an elevator
     * @return new maxFloor value
     */
    private int recalculateMaxFloor() {
        List<Integer> nextFloorNumbers = peopleInElevator.stream().map(Person::getNextFloor).collect(Collectors.toList());
        if (nextFloorNumbers.isEmpty()) {
            return Generator.getMaxFloors();
        } else return Collections.max(nextFloorNumbers);
    }

    /**
     * Moves people inside of elevator from the floor.
     * @param people set of persons who are probably will be moved to an elevator(if elevator has enough space)
     * @param floor current floor
     */
    private void movePeopleToElevator(Set<Person> people, Floor floor) {
        for (Person h : people) {
            int maxPeopleCount = 5;
            if (currentPeopleCount < maxPeopleCount) {
                peopleInElevator.add(h);
                floor.getPeopleOnTheFloor().remove(h);
                currentPeopleCount++;
            }
        }
    }

    /**
     * Method calls every new floor.
     * Moves people from the elevator who need to be on the current floor.
     * Changes "currentFloor" field to a current elevator floor for every person who left elevator.
     * Recalculates currentPeopleCount after actions above.
     * @param direction checks that person movement direction matches with an elevator movement direction
     * @param floor current floor
     * @return set of persons with same movement direction
     */
    private Set<Person> handleFloor(String direction, Floor floor) {
        Set<Person> personSet = peopleInElevator.stream()
                .filter(e -> e.getNextFloor() == currentFloor)
                .peek(e -> e.setCurrentFloor(currentFloor))
                .collect(Collectors.toSet());
        peopleInElevator.removeAll(personSet);
        floor.getPeopleOnTheFloor().addAll(personSet);
        maxFloor = recalculateMaxFloor();
        currentPeopleCount = peopleInElevator.size();
        return floor.getPeopleOnTheFloor().stream()
                .filter(e -> e.getDirection().equals(direction)
                        && e.getCurrentFloor() != e.getNextFloor())
                .collect(Collectors.toSet());
    }

    /**
     * Works if elevator is on the last or first floor.
     * Moves people to elevator.
     * Recalculates maxFloor.
     * Changes elevator direction depending on what floor elevator currently is(if last - "DOWN", first - "UP)
     * @param direction new elevator direction
     * @param floor current elevator floor
     * @param people current set of people inside of elevator
     */
    private void moveElevatorIfOnLastFloor(String direction, Floor floor, Set<Person> people) {
        movePeopleToElevator(people, floor);
        maxFloor = recalculateMaxFloor();
        showFrame(floor);
        this.direction = direction;
    }

    /**
     * Moves people from the floor to an elevator and recalculates maxFloor.
     * Calls method showFrame to output information.
     * @param floor current floor
     * @param people set of people that will be moved into the elevator
     */
    private void moveElevator(Floor floor, Set<Person> people) {
        movePeopleToElevator(people, floor);
        maxFloor = recalculateMaxFloor();
        showFrame(floor);
    }

    /**
     * Method shows every frame of elevator work
     * @param floor frame of floor
     */
    private void showFrame(Floor floor) {
        System.out.println("Current floor: " + floor.getNumber() +
                ", remaining people on the floor: " + floor.getPeopleOnTheFloor().stream().filter(e -> e.getCurrentFloor() != e.getNextFloor()).count() +
                ", current elevator floor: " + currentFloor +
                ", current elevator direction: " + direction +
                ", current people in elevator: " + peopleInElevator.size());
    }
}