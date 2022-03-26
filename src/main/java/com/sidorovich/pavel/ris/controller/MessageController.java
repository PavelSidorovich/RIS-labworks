package com.sidorovich.pavel.ris.controller;

import jakarta.annotation.Resource;
import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.MessageProducer;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import jakarta.jms.Topic;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "MessageController", urlPatterns = "/messages")
public class MessageController extends HttpServlet {

    private static final String THIS_PAGE_PATH = "/WEB-INF/view/message.jsp";
    private static final String CURRENT_PAGE_URL = "/RIS_labworks_main_war_exploded/messages";
    private static final String MESSAGES_REQ_PARAM = "messages";
    private static final String SEMICOLON = ";";

    @Resource(mappedName = "jms/__defaultConnectionFactory")
    private ConnectionFactory cf;

    @Resource(mappedName = "java:app/studentDemo")
    private Topic topic;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(THIS_PAGE_PATH);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String[] messages = req.getParameter(MESSAGES_REQ_PARAM).split( ";[\r]?[\n]?");
        RequestDispatcher dispatcher = req.getRequestDispatcher(THIS_PAGE_PATH);
        try {
            Connection con = cf.createConnection();
            Session ses = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer mp = ses.createProducer(topic);
            TextMessage tm = ses.createTextMessage();
            for (String message : messages) {
                tm.setText(message);
                mp.send(tm);
            }
        } catch (Exception ignored) {
        }
        req.setAttribute("message", "Successful saving to file");
        dispatcher.forward(req, resp);
    }

}
