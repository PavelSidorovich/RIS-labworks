package com.sidorovich.pavel.soap;

import org.apache.axis.client.Service;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.ServiceException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

public class LibraryApp {

    private static final String ENDPOINT = "http://localhost:8080/axis/Library.jws";

    public static void main(String[] args) throws MalformedURLException, ServiceException {
        Service service = new Service();
        Call call = service.createCall();
        call.setTargetEndpointAddress(ENDPOINT);


        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                final String line = getMenuInput(in);
                final String methodName = defineMethodName(line);
                if (methodName.equals("none")) {
                    continue;
                }
                final String title = defineTitle(line);
                final String searchQuery = getSearchValue(in);
                final Object[] params = new Object[] { searchQuery };
                final String[] foundItems = (String[]) call.invoke(QName.valueOf(methodName), params);

                printItems(foundItems, title);
            }
            while (true);
        } catch (RuntimeException ignored) { // exit
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

    private static String defineTitle(String line) {
        switch (line) {
        case "1":
            return  "AUTHORS";
        case "2":
            return "BOOKS";
        default:
            return  "NONE";
        }
    }

    private static String getSearchValue(BufferedReader in) throws IOException {
        System.out.print("Enter search value: ");

        return in.readLine();
    }

    private static String defineMethodName(String line) {
        String methodName;
        switch (line) {
        case "3":
            throw new RuntimeException();
        case "1":
            methodName = "findByBookName";
            break;
        case "2":
            methodName = "findByAuthor";
            break;
        default:
            methodName = "none";
        }
        return methodName;
    }

    private static String getMenuInput(BufferedReader in) throws IOException {
        System.out.println("1 - find by book name");
        System.out.println("2 - find by author");
        System.out.println("3 - exit");

        return in.readLine();
    }

    private static void printItems(String[] items, String title) {
        System.out.println("\n--------------------------------------------");
        System.out.printf("| %40s |\n", title);
        System.out.println("--------------------------------------------");
        for (String item : items) {
            if (item == null) {
                break;
            }
            System.out.printf("| %40s |\n", item);
            System.out.println("--------------------------------------------");
        }
    }

}
