package ovh.cjk.homeautomation.controller.gateway.message;

import ovh.cjk.homeautomation.controller.gateway.message.util.*;

public class Message {

    private int nodeId = 255;
    private int sensorId = 255;
    private boolean ack = false;
    private MessageType type;
    private MessageSubType subtype;
    private String payload;

    public Message(){
        ;
    }

    public Message(String rawString){
        this.parseRawMessage(rawString);
    }

    private void parseRawMessage(String rawString){
        String message[] = rawString.split(";");
        this.nodeId = Integer.parseInt(message[0]);
        this.sensorId = Integer.parseInt(message[1]);
        this.type = MessageType.fromInt(Integer.parseInt(message[2]));
        this.ack = Integer.parseInt(message[3]) != 0;

        int messageSubType = Integer.parseInt(message[4]);
        if(this.type == MessageType.INTERNAL){
            this.subtype = InternalSubType.fromInt(messageSubType);
        } else if ((this.type == MessageType.REQUEST) || (this.type == MessageType.SET)){
            this.subtype = SensorSubType.fromInt(messageSubType);
        } else if ((this.type == MessageType.PRESENTATION)){
            this.subtype = PresentationSubType.fromInt(messageSubType);
        }

        if(message.length > 5){
            this.payload = message[5];
        }
    }

    public String toString(){
        StringBuilder string = new StringBuilder("");
        string.append(nodeId).append(";");
        string.append(sensorId).append(";");
        string.append(type.getValue()).append(";");
        string.append((ack) ? 1 : 0).append(";");
        string.append(subtype.getValue()).append(";");
        string.append(payload);

        return string.toString();
    }

    public String toReadableString(){
        StringBuilder string = new StringBuilder("Message: ");
        string.append("node id: ").append(nodeId);
        string.append(", sensor id: ").append(sensorId);
        string.append(", message type: ").append(type);
        string.append(", ack: ").append(ack);
        string.append(", subtype: ").append(subtype);
        string.append(", payload: ").append(payload);
        return string.toString();
    }

    public String getPayload() {
        return payload;
    }

    public int getNodeId() {
        return nodeId;
    }

    public int getSensorId() {
        return sensorId;
    }

    public boolean isAck() {
        return ack;
    }

    public MessageType getType() {
        return type;
    }

    public MessageSubType getSubtype() {
        return subtype;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public void setAck(boolean ack) {
        this.ack = ack;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public void setSubtype(MessageSubType subtype) {
        this.subtype = subtype;
    }

}
