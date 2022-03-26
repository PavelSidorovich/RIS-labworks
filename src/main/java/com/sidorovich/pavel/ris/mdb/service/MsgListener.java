package com.sidorovich.pavel.ris.mdb.service;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSDestinationDefinition;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

import java.io.File;
import java.io.FileWriter;

@JMSDestinationDefinition(
        name = "java:app/studentDemo", interfaceName = "jakarta.jms.Topic",
        resourceAdapter = "jmsra", destinationName = "studentDemo")
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(
                propertyName = "destinationLookup",
                propertyValue = "java:app/studentDemo"),
        @ActivationConfigProperty(
                propertyName = "destinationType",
                propertyValue = "jakarta.jms.Topic")
})
public class MsgListener implements MessageListener {

    private static final String FILE_PATH =
            "D:\\BSUIR\\6 semester\\RIS\\Labwork2-6\\RIS_labworks\\src\\main\\resources\\output.txt";
    private static final String EXCLAMATION_MARK = "!";
    private static final String NEW_LINE = "\n";

    @Override
    public void onMessage(Message message) {
        File file = new File(FILE_PATH);

        try (FileWriter writer = new FileWriter(file, true)) {
            TextMessage msg = (TextMessage) message;
            String strMsg = msg.getText();
            if (strMsg.contains(EXCLAMATION_MARK)) {
                writer.write(strMsg + NEW_LINE);
            }
        } catch (Exception ignored) {
        }
    }

}
