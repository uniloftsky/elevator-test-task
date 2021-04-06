package elevator;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorTest {

    @Test
    void startElevator() {
        int countPeopleOnTheFloor;
        Building building = new Building(0, 10, 5, 10);
        Elevator elevator = new Elevator(building);
        elevator.startElevator();
        int result = 0;
        for (Floor floor : building.getFloors()) {
            result += floor.getPeopleOnTheFloor().stream().filter(e -> e.getCurrentFloor() != e.getNextFloor()).count();
        }
        countPeopleOnTheFloor = result;
        assertEquals(countPeopleOnTheFloor, 0);
    }

    @Test
    void testCheckPeopleByFloorsCount() {
        List<Floor> floors = Generator.floorsGenerator(0, 10, 2, 5);
        int result = 0;
        for (Floor floor : floors) {
            result += floor.getPeopleOnTheFloor().size();
        }
        assertTrue(result > 0);
    }
}