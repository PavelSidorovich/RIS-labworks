package com.sidorovich.pavel.rmi.server;

import com.sidorovich.pavel.rmi.server.impl.MessageServiceImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Runner {

    private static final int PORT = 8080;
    private static final String SERVER_NAME = "MessageService";

    private static final String SERVER_IS_READY_MESSAGE = "Server is ready!";

    public static void main(String[] args) {
        try {
            MessageService stub = (MessageService) UnicastRemoteObject
                    .exportObject(new MessageServiceImpl(), PORT);
            Registry registry = LocateRegistry.createRegistry(PORT);

            registry.rebind(SERVER_NAME, stub);
            System.out.println(SERVER_IS_READY_MESSAGE);
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
