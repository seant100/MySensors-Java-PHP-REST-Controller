package ovh.cjk.homeautomation.controller.gateway.message;

import ovh.cjk.homeautomation.controller.gateway.message.util.InternalSubType;
import ovh.cjk.homeautomation.controller.gateway.message.util.ResponseBuilder;
import ovh.cjk.homeautomation.controller.rest.NodeManager;
import ovh.cjk.homeautomation.controller.rest.Node;

import java.time.Instant;

import static ovh.cjk.homeautomation.controller.gateway.message.util.MessageType.*;

public class MessageProcessorInternal extends MessageProcessor {

    private static NodeManager nodeManager = new NodeManager();

    public MessageProcessorInternal(Message setMessage){
        if(setMessage.getType() != INTERNAL) {
            throw new IllegalArgumentException("Message must be an internal type in order to be processed by MessageProcessorInternal");
        }
        this.message = setMessage;

        if(this.message.getNodeId() < 255){
            this.node = nodeManager.getById(message.getNodeId());
        }
    }

    public void process(){
        switch((InternalSubType)this.message.getSubtype()) {
            case I_TIME:
                this.processTimeRequest();
            case I_ID_REQUEST:
                this.processIdRequest();
                break;
            case I_CONFIG:
                this.processConfigRequest();
                break;
            case I_BATTERY_LEVEL:
                this.updateNodeBatteryLevel();
                break;
            case I_SKETCH_NAME:
                this.updateNodeSketchName();
                break;
            case I_SKETCH_VERSION:
                this.updateNodeSketchVersion();
                break;
        }
    }

    private void processTimeRequest(){
        long epochTime = Instant.now().getEpochSecond();
        this.sendResponse(ResponseBuilder.createResponse(this.message, Long.toString(epochTime)));
    }

    private void processConfigRequest(){
        this.sendResponse(ResponseBuilder.createResponse(this.message, "M"));
    }

    private void processIdRequest(){
        //add a new node and return the id
        Node node = nodeManager.add();
        if(node != null){
            this.sendResponse(ResponseBuilder.createResponse(this.message, InternalSubType.I_ID_RESPONSE, Integer.toString(node.getId())));
        }
    }

    private void updateNodeBatteryLevel(){
        if(this.node != null){
            node.setBatteryPercent(Integer.parseInt(message.getPayload()));
            nodeManager.update(node);
        }
    }

    private void updateNodeSketchName(){
        if((this.node != null) && !(this.node.getSketchName().equals(message.getPayload()))){
            node.setSketchName(message.getPayload());
            nodeManager.update(node);
        }
    }

    private void updateNodeSketchVersion(){
        if((this.node != null) && !(this.node.getSketchVersion().equals(message.getPayload()))){
            node.setSketchVersion(message.getPayload());
            nodeManager.update(node);
        }
    }

}
