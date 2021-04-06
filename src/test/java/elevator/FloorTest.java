package elevator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

public class FloorTest {

    @Test
    void testGetFloorNumber() {
        Floor floor = new Floor(2, new HashSet<>());
        assertEquals(floor.getNumber(), 2);
    }

    @Test
    void testGetPeopleOnTheFloor() {
        Set<Person> personSet = Generator.peopleGenerator(1, 0, 10, 5);
        Floor floor = new Floor(1, personSet);
        assertEquals(floor.getPeopleOnTheFloor(), personSet);
    }
}
