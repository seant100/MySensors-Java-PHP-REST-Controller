package ovh.cjk.homeautomation.controller;

import ovh.cjk.homeautomation.controller.gateway.SocketClient;

public class Main {

    public static void main(String[] args){
        System.out.println("Starting Gateway Controller...");
        SocketClient.getInstance();
    }
}
