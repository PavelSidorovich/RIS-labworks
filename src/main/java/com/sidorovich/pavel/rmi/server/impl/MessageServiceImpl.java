package com.sidorovich.pavel.rmi.server.impl;

import com.sidorovich.pavel.rmi.server.MessageService;

public class MessageServiceImpl implements MessageService {

    private static final String OLD_CHAR = "[aA]";
    private static final String NEW_CHAR = "";

    @Override
    public String excludeLetters(String message) {
        return message.replaceAll(OLD_CHAR, NEW_CHAR);
    }

}
