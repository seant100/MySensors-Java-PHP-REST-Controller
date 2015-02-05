package ovh.cjk.homeautomation.controller.rest;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.*;

public class NodeManagerTest {

    private NodeManager nodeManager;

    @Before
    public void setUp(){
        nodeManager = new NodeManager();
    }

    @Test
    public void getAllNodesShouldSucceed() throws Exception{
        ArrayList<Node> nodes = nodeManager.getAll();

        assertNotEquals(0, nodes.size());
    }

    @Test
    public void getNodeWithIdShouldSucceed() throws Exception{
        Node node = nodeManager.getById(1);

        assertNotNull(node);
        assertEquals(1, node.getId());
    }

    @Test
    public void getNodeWithNonExistingIdShouldReturnNull() throws Exception{
        Node node = nodeManager.getById(999999);
        assertNull(node);
    }

    @Test
    public void addNodeShouldSucceed() throws Exception {
        Node node = nodeManager.add();
        assertNotNull(node);
    }

    @Test
    public void updatingNodeShouldSucceed() throws Exception {
        Node node = nodeManager.getById(1);
        assertNotNull(node);

        node.setName(UUID.randomUUID().toString());
        node.setSketchName(UUID.randomUUID().toString());
        node.setSketchVersion(UUID.randomUUID().toString());

        Node updatedNode = nodeManager.update(node);

        assertEquals(node.getId(), updatedNode.getId());
        assertEquals(node.getName(), updatedNode.getName());
        assertEquals(node.getSketchName(), updatedNode.getSketchName());
        assertEquals(node.getSketchVersion(), updatedNode.getSketchVersion());
    }

}