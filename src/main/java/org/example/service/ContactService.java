package org.example.service;

import org.example.domain.Contact;
import org.example.persistance.ContactRepository;
import org.example.transfer.CreateContactRequest;
import org.example.transfer.UpdateContactRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ContactService {

    private ContactRepository contactRepository = new ContactRepository();

    public void createContact(CreateContactRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Creating contact: " + request);

        contactRepository.createContact(request);
    }

    public void updateContact(long idContact, UpdateContactRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Updating contact " + idContact + ": " + request);
        contactRepository.updateContact(idContact, request);
    }


    public void deleteContact(long idContact) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting contact " + idContact);
        contactRepository.deleteContact(idContact);
    }


    public List<Contact> getContacts() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retrieving task ");
        return contactRepository.getContacts();
    }

    ////aici
    public List<Contact> getContacts(String firstName, UpdateContactRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Searching for contacts with first name " + firstName);
        return contactRepository.getContacts(firstName, request);
    }

}
