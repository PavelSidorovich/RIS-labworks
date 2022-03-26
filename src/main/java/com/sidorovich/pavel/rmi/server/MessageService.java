package com.sidorovich.pavel.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessageService extends Remote {

    String excludeLetters(String message) throws RemoteException;

}
