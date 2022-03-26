package com.sidorovich.pavel.lw7;

import com.sidorovich.pavel.lw7.service.FibonacciService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Labwork7Application {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        FibonacciService fibonacciService = context.getBean("fibonacciService", FibonacciService.class);
        System.out.print("First found fibonacci number after " + fibonacciService.getCeil() + ": ");
        fibonacciService.numbers().stream()
                        .filter(num -> num >= fibonacciService.getCeil())
                        .findFirst()
                        .ifPresent(System.out::println);
    }

}
