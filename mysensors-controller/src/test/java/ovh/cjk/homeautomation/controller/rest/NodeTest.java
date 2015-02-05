package ovh.cjk.homeautomation.controller.rest;

import org.junit.BeforeClass;
import org.junit.Test;

import ovh.cjk.homeautomation.controller.rest.exception.SensorAlreadyExistsException;

import static org.junit.Assert.*;

public class NodeTest {

    private static Node node;

    private static final String NODE_NAME = "NODE_NAME";
    private static final String NODE_SKETCH_NAME = "NODE_SKETCH_NAME";
    private static final String NODE_SKETCH_VERSION = "NODE_SKETCH_VERSION";

    @BeforeClass
    public static void setUp(){
        node = new NodeManager().add();
    }

    @Test
    public void shouldBeAbleToUpdateLocalNodeAttributes(){
        node.setName(NODE_NAME);
        node.setSketchName(NODE_SKETCH_NAME);
        node.setSketchVersion(NODE_SKETCH_VERSION);
        assertEquals(NODE_NAME, node.getName());
        assertEquals(NODE_SKETCH_NAME, node.getSketchName());
        assertEquals(NODE_SKETCH_VERSION, node.getSketchVersion());
    }

    @Test
    public void shouldBeAbleToUpdateBatteryPercent(){
        node.setBatteryPercent(77);
        assertEquals(77, node.getBatteryPercent());
    }

    @Test
    public void addSensorToNodeShouldSucceed() throws SensorAlreadyExistsException{
        assertNull(node.getSensorById(1));

        Sensor sensor = node.addSensor(1);
        assertEquals(1, sensor.getId());

        assertNotNull(node.getSensorById(1));
    }

    @Test (expected=SensorAlreadyExistsException.class)
    public void addSensorToNodeWithIdThatAlreadyExistsShouldFail() throws SensorAlreadyExistsException{
        node.addSensor(2);
        node.addSensor(2);
    }
}