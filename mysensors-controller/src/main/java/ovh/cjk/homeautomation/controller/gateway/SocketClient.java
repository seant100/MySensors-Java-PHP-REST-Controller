package ovh.cjk.homeautomation.controller.gateway;

import ovh.cjk.homeautomation.controller.gateway.message.Message;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public final class SocketClient {

    private static SocketClient instance = new SocketClient();
    private static Socket socket;
    private IncomingSocketListener incomingSocketListener;
    private DataOutputStream clientOutput;

    private static final String GATEWAY_IP = "192.168.1.10";
    private static final int GATEWAY_PORT = 5003;

    private SocketClient(){
        try{
            socket = new Socket(GATEWAY_IP, GATEWAY_PORT);
            socket.setKeepAlive(true);
            if(socket != null){
                clientOutput = new DataOutputStream(socket.getOutputStream());
                incomingSocketListener = new IncomingSocketListener(socket.getInputStream());
                incomingSocketListener.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SocketClient getInstance(){
        return instance;
    }

    public void sendMessage(Message message){
        System.out.println("Sending: " + message.toString());
        try {
            clientOutput.write(message.toString().getBytes());
            clientOutput.write('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
