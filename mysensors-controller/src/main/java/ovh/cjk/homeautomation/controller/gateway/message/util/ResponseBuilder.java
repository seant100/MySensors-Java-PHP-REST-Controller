package ovh.cjk.homeautomation.controller.gateway.message.util;

import ovh.cjk.homeautomation.controller.gateway.message.Message;

public class ResponseBuilder {

    public static Message createResponse(Message incoming){
        Message outgoing = new Message();

        outgoing.setNodeId(incoming.getNodeId());
        outgoing.setSensorId(incoming.getSensorId());
        outgoing.setType(incoming.getType());
        outgoing.setSubtype(incoming.getSubtype());

        return outgoing;
    }

    public static Message createResponse(Message incoming, String payload){
        Message outgoing = createResponse(incoming);
        outgoing.setPayload(payload);
        return outgoing;
    }

    public static Message createResponse(Message incoming, MessageSubType subtype, String payload){
        Message outgoing = createResponse(incoming, payload);
        outgoing.setSubtype(subtype);
        return outgoing;
    }

}
