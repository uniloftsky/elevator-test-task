package elevator;

import java.util.*;

/**
 * Building is a class that holding floors with people
 */
public class Building {

    private List<Floor> floors;

    /**
     * Field "floors" generates via static Generator method called floorsGenerator.
     * @see Generator
     * @param minPeopleCount min quantity of people per floor
     * @param maxPeopleCount max quantity of people per floor
     * @param minFloorsCount min quantity of floors
     * @param maxFloorsCount max quantity of floors
     */
    public Building(int minPeopleCount, int maxPeopleCount, int minFloorsCount, int maxFloorsCount) {
        this.floors = Generator.floorsGenerator(minPeopleCount, maxPeopleCount, minFloorsCount, maxFloorsCount);
    }

    public List<Floor> getFloors() {
        return floors;
    }
}
