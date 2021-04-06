package elevator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BuildingTest {

    @Test
    void testBuildingFloors() {
        Building building = new Building(0, 10, 5, 10);
        assertFalse(building.getFloors().isEmpty());
    }
}
