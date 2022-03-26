package com.sidorovich.pavel.rmi.client;

import com.sidorovich.pavel.rmi.server.MessageService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RmiClient {

    private static final int PORT = 8080;
    private static final String SERVER_NAME = "MessageService";

    private static final Scanner scanner = new Scanner(System.in);
    private static final String EXIT_COMMAND = "!q";
    private static boolean isExit = false;

    public static void main(String[] args) {
        try {
            final Registry registry = LocateRegistry.getRegistry(PORT);
            MessageService server = (MessageService) registry.lookup(SERVER_NAME);

            while (!isExit) {
                excludeLetters(server);
            }
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    private static void excludeLetters(MessageService server) throws RemoteException {
        System.out.print("Please, enter a message (or !q to exit): ");
        String message = scanner.nextLine();
        isExit = EXIT_COMMAND.equals(message);
        System.out.printf("Message without letters 'a': %s%n", server.excludeLetters(message));
    }

}
