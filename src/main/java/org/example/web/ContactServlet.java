package org.example.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.domain.Contact;
import org.example.service.ContactService;
import org.example.transfer.CreateContactRequest;
import org.example.transfer.UpdateContactRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/contacts")
public class ContactServlet extends HttpServlet {

    private ContactService contactService = new ContactService();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CreateContactRequest request = new ObjectMapper().readValue(req.getReader(), CreateContactRequest.class);
        try {
            contactService.createContact(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "There was an error while processing your request." + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idContact = req.getParameter("idContact");

        UpdateContactRequest request = new ObjectMapper().readValue(req.getReader(), UpdateContactRequest.class);

        try {
            contactService.updateContact(Long.parseLong(idContact), request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "There was an error while processing your request." + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idContact = req.getParameter("idContact");

        try {
            contactService.deleteContact(Long.parseLong(idContact));
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "There was an error while processing your request." + e.getMessage());
        }
    }

    //aici
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idContact = req.getParameter("id");
        String firstName = req.getParameter("firstName");
        UpdateContactRequest request = new ObjectMapper().readValue(req.getReader(), UpdateContactRequest.class);
        try {
            if (idContact == null) {
                List<Contact> contacts = contactService.getContacts(firstName, request);
                new ObjectMapper().writeValue(resp.getWriter(), contacts);
            } else {

                List<Contact> contacts = contactService.getContacts();
                new ObjectMapper().writeValue(resp.getWriter(), contacts);
            }

        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "There was an error while processing your request." + e.getMessage());
        }


    }
}

