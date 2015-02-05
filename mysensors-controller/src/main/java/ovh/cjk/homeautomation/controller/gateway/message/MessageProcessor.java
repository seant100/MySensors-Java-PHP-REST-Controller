package ovh.cjk.homeautomation.controller.gateway.message;

import ovh.cjk.homeautomation.controller.gateway.SocketClient;
import ovh.cjk.homeautomation.controller.rest.Node;

public abstract class MessageProcessor {

    protected Message message;
    protected Node node;

    public abstract void process();

    protected void sendResponse(Message response){
        SocketClient.getInstance().sendMessage(response);
    }

}
