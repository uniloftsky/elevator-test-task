package elevator;

import java.util.*;

/**
 * Generator is a class for randomly generating a quantity of floors and people per floor referring to a range.
 */
public class Generator {

    private static int maxFloors;
    private static int min, max, diff;
    private static final Random random = new Random();

    /**
     * This method randomly generates every Person.
     * Every person has currentFloor field that matches with current generated floor.
     * Every person has nextFloor field that randomly generates and doesn't match with currentFloor.
     *
     * @param currentFloor   current generated floor
     * @param minPeopleCount min quantity of people on current floor
     * @param maxPeopleCount max quantity of people on current floor
     * @param maxFloors      quantity of all floors in the building
     * @return set of persons
     * @throws RuntimeException if ranges are invalid
     */
    public static Set<Person> peopleGenerator(int currentFloor, int minPeopleCount, int maxPeopleCount, int maxFloors) throws RuntimeException {

        //checking for a valid currentFloor
        if (currentFloor < 0 || currentFloor > maxFloors) {
            throw new RuntimeException("Invalid current floor value!");
        }
        Set<Person> peopleOnTheFloor = new HashSet<>();
        min = minPeopleCount;
        max = maxPeopleCount;

        //checking for a valid ranges
        if (min < 0 || max < 0) {
            throw new RuntimeException("Invalid range for people generator!");
        }
        diff = max - min;
        int peopleCount = random.nextInt(diff + 1) + min;
        int nextFloor;
        for (int i = 1; i <= peopleCount; i++) {
            min = 1;
            max = maxFloors;
            diff = max - min;
            nextFloor = random.nextInt(diff + 1) + min;

            //if generated nextFloor value that matches currentFloor, generate it again
            while (nextFloor == currentFloor) {
                nextFloor = random.nextInt(diff + 1) + min;
            }
            peopleOnTheFloor.add(new Person(currentFloor, nextFloor));
        }
        return peopleOnTheFloor;
    }

    /**
     * This method randomly generates floors and quantity of floors referring to a range.
     * Every floor generating with using peopleGenerator().
     *
     * @param minPeopleCount - min quantity of people per floor
     * @param maxPeopleCount - max quantity of people per floor
     * @param minFloorsCount - min quantity of floors in the building
     * @param maxFloorsCount - max quantity of floors in the building
     * @return list of floors
     */
    public static List<Floor> floorsGenerator(int minPeopleCount, int maxPeopleCount, int minFloorsCount, int maxFloorsCount) {
        List<Floor> floors = new ArrayList<>();
        min = minFloorsCount;
        max = maxFloorsCount;

        //checking for a valid floors range
        if (min <= 0 || max <= 0) {
            throw new RuntimeException("Invalid range for floors count!");
        }
        diff = max - min;
        int floorsCount = random.nextInt(diff + 1) + min;
        maxFloors = floorsCount;
        for (int i = 1; i <= floorsCount; i++) {
            floors.add(new Floor(i, Generator.peopleGenerator(i, minPeopleCount, maxPeopleCount, maxFloors)));
        }
        return floors;
    }

    /**
     * Method for getting quantity of all floors in the building
     *
     * @return quantity of floors
     */
    public static int getMaxFloors() {
        return maxFloors;
    }
}
