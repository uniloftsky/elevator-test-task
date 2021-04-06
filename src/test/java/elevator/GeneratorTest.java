package elevator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {

    private int minPeopleCount, maxPeopleCount, minFloorsCount, maxFloorsCount, currentFloor, maxFloor;

    @BeforeEach
    void setUp() {
        minPeopleCount = 0;
        maxPeopleCount = 10;
        minFloorsCount = 5;
        maxFloorsCount = 10;
        currentFloor = 1;
        maxFloor = maxFloorsCount;
    }

    @Test
    void testPeopleGeneratorByCurrentFloor() {
        assertThrows(RuntimeException.class, () -> Generator.peopleGenerator(-1, minPeopleCount, maxPeopleCount, maxFloor));
    }

    @Test
    void testPeopleGeneratorByMinPeopleCount() {
        assertThrows(RuntimeException.class, () -> Generator.peopleGenerator(currentFloor, -1, maxPeopleCount, maxFloor));
    }

    @Test
    void testPeopleGeneratorByMaxPeopleCount() {
        assertThrows(RuntimeException.class, () -> Generator.peopleGenerator(currentFloor, minPeopleCount, -1, maxFloor));
    }

    @Test
    void testPeopleGeneratorByMaxFloor() {
        assertThrows(RuntimeException.class, () -> Generator.peopleGenerator(currentFloor, minPeopleCount, maxPeopleCount, -1));
    }

    @Test
    void testFloorsGenerator() {
        minFloorsCount = -1;
        assertThrows(RuntimeException.class, () -> Generator.floorsGenerator(minPeopleCount, maxPeopleCount, minFloorsCount, maxFloorsCount));
        maxFloorsCount = -1;
        assertThrows(RuntimeException.class, () -> Generator.floorsGenerator(minPeopleCount, maxPeopleCount, minFloorsCount, maxFloorsCount));
    }
}