package elevator;

/**
 * Client`s code.
 * Setting parameters for generating floors and people on the floors.
 * Creating building, elevator and starting main logic.
 */

public class Main {

    public static void main(String[] args) {
        int minPeopleCount = 0, maxPeopleCount = 10, minFloorsCount = 5, maxFloorsCount = 10;

        Building building = new Building(minPeopleCount, maxPeopleCount, minFloorsCount, maxFloorsCount);
        Elevator elevator = new Elevator(building);

        elevator.startElevator();
    }

}