package elevator;

/**
 * Person class is a single person who is generated on every floor
 */
public class Person {

    private int currentFloor;
    private int nextFloor;
    private String direction;

    public Person(int currentFloor, int nextFloor) {
        this.currentFloor = currentFloor;
        this.nextFloor = nextFloor;
        this.direction = calculateDirection();
    }

    /**
     * This method is used to calculate the movement direction of a person in the elevator
     * Move direction is used for further elevator logic
     * @return a direction of person move in the elevator
     */
    private String calculateDirection() {
        if (nextFloor > currentFloor) {
            return "UP";
        } else {
            return "DOWN";
        }
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getNextFloor() {
        return nextFloor;
    }

    public String getDirection() {
        return direction;
    }
}
