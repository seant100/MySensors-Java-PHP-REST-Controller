package ovh.cjk.homeautomation.controller.gateway;

import ovh.cjk.homeautomation.controller.gateway.message.Message;
import ovh.cjk.homeautomation.controller.gateway.message.MessageProcessor;
import ovh.cjk.homeautomation.controller.gateway.message.MessageProcessorInternal;
import ovh.cjk.homeautomation.controller.gateway.message.util.MessageType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Runnable;
import java.lang.String;
import java.lang.System;
import java.lang.Thread;

public class IncomingSocketListener implements Runnable {

    private BufferedReader clientIncomingReader;
    private Thread thread;

    IncomingSocketListener(InputStream inputStream){
        clientIncomingReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public void run() {
        while (true) {
            try {
                String input = clientIncomingReader.readLine();
                Message message = new Message(input);
                System.out.println(message.toReadableString());

                if(message.getType() == MessageType.INTERNAL){
                    MessageProcessor processor = new MessageProcessorInternal(message);
                    processor.process();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void start(){
        System.out.println("Starting IncomingSocketListener" );
        if (thread == null)
        {
            thread = new Thread (this, "IncomingSocketListener");
            thread.start ();
        }
    }
}
