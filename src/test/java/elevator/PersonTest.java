package elevator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testGetNextFloor() {
        Person person = new Person(1, 5);
        assertEquals(person.getNextFloor(), 5);
    }

    @Test
    void testGetDirection() {
        Person person = new Person(1, 5);
        assertEquals(person.getDirection(), "UP");
    }
}