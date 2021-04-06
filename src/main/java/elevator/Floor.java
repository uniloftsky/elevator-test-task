package elevator;

import java.util.Set;

public class Floor {

    private int number;
    private Set<Person> peopleOnTheFloor;

    public Floor(int number, Set<Person> peopleOnTheFloor) {
        this.number = number;
        this.peopleOnTheFloor = peopleOnTheFloor;
    }

    public int getNumber() {
        return number;
    }

    public Set<Person> getPeopleOnTheFloor() {
        return peopleOnTheFloor;
    }
}
